package threadtest;

import java.util.concurrent.TimeUnit;

public class VolatileExample {

    private static boolean flag = false;
//    private static volatile boolean flag = false;
    private static int i = 0;
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                flag = true;
                System.out.println("flag 被修改成 true");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        
        while (!flag) {
            // 如果把i改成包装类型，主线程在 i++ 中更新 i 的引用时，可能顺带读取到新线程修改的 flag = true。
            i++;
        }
        
        System.out.println("程序结束,i=" + i);
    }
}
