package threadtest.tools;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wuhp on 2018/3/1
 */
public class CountDownLatchDemo {
    public static void testCountDownLatch() {

        int threadCount = 10;

        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("新增加线程" + i);
            System.out.println("还剩余" + latch.getCount() + "未准备！");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getId() + "在执行。。。。");
                }
            }).start();
            latch.countDown();
        }
        System.out.println("10个线程已准备完毕，准备开启10个线程的并发~~~");
    }

    public static void main(String[] args) {
        CountDownLatchDemo.testCountDownLatch();
    }
}
