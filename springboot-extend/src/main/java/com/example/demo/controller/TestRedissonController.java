package com.example.demo.controller;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * todow 添加类描述
 *
 * @author hepeng.wu@going-link.com 2022/6/23 下午2:25
 */
@Controller
@RequestMapping("/redisson")
public class TestRedissonController {

    //    @Resource
//    private RedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redisson;

    //缓存里面的数据如何和数据库保持一致 缓存数据一致性 1)、双写模式 2)、失效模式
//    public Map<String, List<Catelog2Vo>> getCatelogJsonFromDbWithRedissonLock() {
//
//        //1、锁的名字，锁的粒度，越细越快。
//        //2、锁的粒度:具体缓存的是某个数据，11‐号商品。product‐11‐lock product‐12‐ lock
//
//        RLock lock = redisson.getLock("catelogJson‐lock");
//        lock.lock();
//        Map<String, List<Catelog2Vo>> dataFromDb;
//        try {
//            dataFromDb = getDataFromDb();
//        } finally {
//            lock.unlock();
//        }
//        return dataFromDb;
//    }

    // 可重入锁
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        //1、获取一把锁，只要锁的名字一样，就是同一把锁
        RLock lock = redisson.getLock("redisson:my‐lock");
        //2、加锁
//        lock.lock();
        //阻塞式等待。默认加锁30s时间
        //1)、锁的自动续期，如果业务超长，运行期间自动给锁续上新的30s。不用担心业务时间长，锁自动过期删掉
        //2)、加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s 以后自动删除

        lock.lock(10, TimeUnit.SECONDS);
        //10s钟自动解锁，自动解锁时间一定要大于业务执行时间
        //问题:lock.lock(10, TimeUnit.SECONDS); 在锁时间到了以后，不会自动续期
        //1、如果我们传递了锁的超时时间，就发送给redis执行脚本，进行占锁，默认超时就 我们指定的时间
        //2、如果我们未指定超时间件，就使用 30 * 1000【LockWatchdogTimeout看门狗的 默认时间】
        // 只要占锁成功，就会启动一个定时任务。【重新给锁设置过期时间，新的过期时间就 是看门狗的默认时间】,每隔10s都会自动再次续期，续成满时间
        // internalLockLeaseTime / 3 【看门狗时间 / 3，10s】 26
        //最佳实战
        //1)、lock.lock(10, TimeUnit.SECONDS); 省掉了整个续期操作。手动解锁
        try {
            System.out.println("加锁成功，执行业务..." + Thread.currentThread().getId());
            Thread.sleep(60000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //3、解锁 假设解锁代码没有运行，redisson会不会出现问题
            System.out.println("释放锁..." + Thread.currentThread().getId());
            lock.unlock();
//            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
//                lock.unlock();
//            }
        }
        return "hello";
    }

    //保证一定能读到最新数据，修改期间，写锁是一个排他锁(互斥锁，独享锁)。读锁是 一个共享锁
    //写锁没释放，读就必须等待
    //读 + 读，相当于无锁，并发读，只会在redis中记录好，所有当前的读锁，他们都会时加锁成功
    //写 + 读，等待写锁释放
    //写 + 写，阻塞方式
    //读 + 写，有读锁，写也需要等待
    //只要有写的存在，都必须等待
    @ResponseBody
    @GetMapping("/write")
    public String writeValue() {
        RReadWriteLock lock = redisson.getReadWriteLock("rw‐lock");
        String s = "";
        RLock rLock = lock.writeLock();
        //1、改数据加写锁，读数据加读锁
        rLock.lock();
        System.out.println("写锁加锁成功..." + Thread.currentThread().getId());
        try {
            s = UUID.randomUUID().toString();
            Thread.sleep(30000);
//            redisson.getBu().set("writeValue", s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            System.out.println("写锁释放..." + Thread.currentThread().getId());
        }
        return s;
    }

    @ResponseBody
    @GetMapping("/read")
    public String readValue() {
        RReadWriteLock lock = redisson.getReadWriteLock("rw‐lock");
        String s = "";
        //加读锁
        RLock rLock = lock.readLock();
        rLock.lock();
        System.out.println("读锁加锁成功..." + Thread.currentThread().getId());
        try {
            Thread.sleep(30000);
            s = redisson.getBucket("writeValue").toString();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            rLock.unlock();
            System.out.println("读锁释放..." + Thread.currentThread().getId());
        }
        return s;
    }

    /**
     * 信号量
     * 车库停车
     * 3车位
     * 信号量也可以用作分布式限流
     */
    @ResponseBody
    @GetMapping("/park")
    public String park() throws InterruptedException {
        RSemaphore park = redisson.getSemaphore("park");
        park.acquire();//获取一个信号，获取一个值，占一个车位,阻塞式等待
        boolean b = park.tryAcquire(); //非阻塞式等待
        if (b) {
            //执行业务
        } else {
            return "error";
        }
        return "ok=>" + b;
    }

    @ResponseBody
    @GetMapping("/go")
    public String go() {
        RSemaphore park = redisson.getSemaphore("park");
        park.release();
        //释放一个信号，释放一个车位 return "ok";
        return "ok";
    }

    /**
     * 闭锁
     * 放假，锁门
     * 1班没人了
     * 5个班全部走完，我们可以锁大门
     */
    @ResponseBody
    @GetMapping("/lockDoor")
    public String lockDoor() throws InterruptedException {
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.trySetCount(5);
        door.await(); //等待闭锁都完成
        return "放假了...";
    }

    @ResponseBody
    @GetMapping("/gogogo/{id}")
    public String gogogo(@PathVariable("id") Long id) {
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.countDown(); //计数减一
        return id + "班的人都走了...";
    }
}
