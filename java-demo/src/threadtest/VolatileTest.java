package threadtest;

import java.util.concurrent.TimeUnit;

/**
 * Created by wuhp on 2018/3/17
 * 加volatile保证（线程）可见性，不保证原子性
 */
public class VolatileTest {
    //    a.volatile关键字为域变量的访问提供了一种免锁机制，
    //    b.使用volatile修饰域相当于告诉虚拟机该域可能会被其他线程更新，
    //    c.因此每次使用该域就要重新计算，而不是使用寄存器中的值
    //    d.volatile不会提供任何原子操作，它也不能用来修饰final类型的变量

    /**
     * 不加volatile while(){ ... }非空循环，cpu有时间就行缓存刷新，置为true时会停止
     */
    private boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.test();
        TimeUnit.SECONDS.sleep(1);
        volatileTest.stop = true;
    }

    protected void test() {
        new Thread(() -> {
            int i = 0;
            while (!stop) {
                // 新的cpu在执行代码块的时候有空闲时间会刷新缓冲区
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                i++;
//                System.out.println("i=" + i);
            }
            System.out.println(Thread.currentThread().getName() + "End!");
        }).start();
    }
}
