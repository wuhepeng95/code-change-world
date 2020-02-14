package threadtest.tools;

/**
 * Created by whp on 2018/9/4
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchDemo2 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程树
    public static int threadTotal = 200;
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch latch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add();
                        System.out.println(count);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }
            });
        }
        System.out.println(count);
        latch.await();
        executorService.shutdown();
        System.out.println(count);
    }

    private static void add() {
        count.incrementAndGet();
    }
}

