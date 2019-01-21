package threadtest.threadPoolDemo;

import java.util.concurrent.TimeUnit;

public class CachedThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("创建" + Thread.currentThread().getName());
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getId() + "在执行。。。。");
                }
            }).start();
            Thread.sleep(5000);
        }
    }
}
