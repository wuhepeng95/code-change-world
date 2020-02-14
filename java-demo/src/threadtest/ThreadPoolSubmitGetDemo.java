package threadtest;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSubmitGetDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = new ThreadPoolExecutor(2, 5,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        HashMap<Integer, Future> result = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            result.put(i, executorService.submit(new MyCallable()));
        }

        result.forEach(
                (key, value) -> {
                    try {
                        System.out.println(key + "- " + value.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );

        executorService.shutdown();
    }
}

class MyCallable implements Callable {
    @Override
    public Integer call() {
        System.out.println("线程" + Thread.currentThread().getId() + "开始执行");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("线程" + Thread.currentThread().getId() + "执行中。。。。");
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getId() + "执行结束");
        return new Random().nextInt(8);
    }
}
