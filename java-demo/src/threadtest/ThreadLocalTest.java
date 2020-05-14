package threadtest;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程共享 线程隔离 map（线程hash，值）：不同线程中操作不同treadLocal时，互不影响
 * Created by whp on 2019-01-29
 * <p>
 * 扩展 NDC （ Nested Diagnostic Context ）和 MDC （ Mapped Diagnostic Context ）
 * ● NDC的实现是用hashtable来存储每个线程的stack信息
 * ● MDC的实现是使用ThreadLocal(InheritableThreadLocal)来保存每个线程的Hashtable的类似map的信息
 *
 * @see Hashtable
 * @see ThreadLocal 不继承父线程
 * @see InheritableThreadLocal 继承父线程（只是继承，父线程后续修改不影响）
 */
public class ThreadLocalTest {

    //    private static ThreadLocal<String> userId = ThreadLocal.withInitial(() -> "init_id");
    private static ThreadLocal<String> userId = new InheritableThreadLocal<>();

    static {
        userId.set("init_id");
    }

    private static ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        threadPoolExecutor.submit(() -> {
            try {
                // 线程1两秒之后获得userid，并且设置userid为id1
                TimeUnit.SECONDS.sleep(1);
                System.out.println("THREAD-1 initial userId:" + userId.get());

                userId.set("THREAD-1 id1");
                System.out.println("THREAD-1 set userId id1");

                TimeUnit.SECONDS.sleep(2);
                System.out.println("THREAD-1 now userId :" + userId.get());

                new ThreadLocalTest().testInheritable();
                new ThreadLocalInherited().testInheritable(userId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPoolExecutor.submit(() -> {
            try {
                // 线程二获取初始的userId，然后两秒之后设置为id2，再过两秒之后再次读取userid
                TimeUnit.SECONDS.sleep(2);
                System.out.println("THREAD-2 initial userId :" + userId.get());

                userId.set("THREAD-2 id2");
                System.out.println("THREAD-2 set-id2");

                TimeUnit.SECONDS.sleep(2);
                System.out.println("THREAD-2 now userId :" + userId.get());

                new ThreadLocalTest().testInheritable();
                new ThreadLocalInherited().testInheritable(userId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        while (true) {
            System.out.println(userId.get());
            Thread.sleep(3000);
            userId.set("init_id main");
        }
    }

    public void testInheritable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Test inheritable: " + userId.get());
            }
        }).start();
    }
}
