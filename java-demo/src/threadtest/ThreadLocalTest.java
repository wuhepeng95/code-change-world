package threadtest;

import java.util.concurrent.TimeUnit;

/**
 * Created by whp on 2019-01-29
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> userId = ThreadLocal.withInitial(() -> "init_id");

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                // 线程1两秒之后获得userid，并且设置userid为id1
                TimeUnit.SECONDS.sleep(2);
                System.out.println("initial userId in thread1:" + userId.get());
                userId.set("set-id1-thread1");
                System.out.println("thread1 set userId id1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                // 线程二获取初始的userId，然后一秒之后设置为id2，再过两秒之后再次读取userid
                System.out.println("initial userId in thread2:" + userId.get());
                TimeUnit.SECONDS.sleep(1);
                userId.set("set-id2-thread2");
                System.out.println("thread2 set userId id2");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("now userId in thread2:" + userId.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();        // 在main线程等待两个线程执行结束
        thread1.join();
        thread2.join();
    }
}
