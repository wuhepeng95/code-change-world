package threadtest.sync;

/**
 * Created by whp on 2018/9/4
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncDemo {
    public static int count = 0;
    public static int clientTotal = 500;

    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    add();
                }
            });
        }
        // 输出count的结果：总是小于500
        System.out.println(count);
    }

    // 加上synchronized就可以实现
    public static void add() {
        count++;
    }
}
