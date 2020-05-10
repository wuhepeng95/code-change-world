package threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程共享 线程隔离 map（线程hash，值）
 * Created by whp on 2019-01-29
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> userId = ThreadLocal.withInitial(() -> "init_id");
    private static ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        threadPoolExecutor.submit(() -> {
            try {
                // 线程1两秒之后获得userid，并且设置userid为id1
                TimeUnit.SECONDS.sleep(2);
                System.out.println("THREAD-1 initial userId:" + userId.get());
                userId.set("THREAD-1 set-id1");
                System.out.println("THREAD-1 set userId id1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPoolExecutor.submit(() -> {
            try {
                // 线程二获取初始的userId，然后一秒之后设置为id2，再过两秒之后再次读取userid
                System.out.println("THREAD-2 initial userId:" + userId.get());
                TimeUnit.SECONDS.sleep(1);
                userId.set("THREAD-2 set-id2");
                System.out.println("THREAD-2 set userId id2");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("THREAD-2 now userId:" + userId.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
