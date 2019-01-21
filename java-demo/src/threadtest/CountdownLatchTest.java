package threadtest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 该程序用来模拟发送命令与运行命令，主线程代表指挥官。新建3个线程代表战士，战士一直等待着指挥官下达命令，
 * 若指挥官没有下达命令，则战士们都必须等待。
 * 一旦命令下达，战士们都去运行自己的任务。指挥官处于等待状态，战士们任务运行完成则报告给
 * 指挥官。指挥官则结束等待。
 */
public class CountdownLatchTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool(); //创建一个线程池
        final CountDownLatch cdOrder = new CountDownLatch(1);//指挥官的命令。设置为1，指挥官一下达命令。则cutDown,变为0，战士们运行任务
        final CountDownLatch cdAnswer = new CountDownLatch(10);//由于有三个战士，所以初始值为3，每个战士运行任务完成则cutDown一次，当三个都运行完成，变为0。则指挥官停止等待。
        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                try {
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "正准备接受命令");
                    cdOrder.await(); //战士们都处于等待命令状态
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "已接受命令");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "回应命令处理结果");

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdAnswer.countDown(); //任务运行完成，返回给指挥官，cdAnswer减1。
                }
            };
            service.execute(runnable);//为线程池加入任务
        }
        try {
            Thread.sleep((long) (Math.random() * 1000));

            System.out.println("线程" + Thread.currentThread().getName() +
                    "即将公布命令");
            cdOrder.countDown(); //发送命令，cdOrder减1，处于等待的战士们停止等待转去运行任务。
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已发送命令，正在等待结果");
            cdAnswer.await(); //命令发送后指挥官处于等待状态。一旦cdAnswer为0时停止等待继续往下运行
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已收到全部响应结果");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        service.shutdown(); //任务结束。停止线程池的全部线程
    }
}