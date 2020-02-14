package threadtest.sync;

import java.util.concurrent.TimeUnit;

/**
 * Created by whp on 2019-01-30
 * Case：线程的排他性
 */
public class SyncTest {
    public static void main(String[] args) {
        new SyncTest().test();

    }

    public void test() {
        InnerClass innerClass = new InnerClass();

        // thread1和thread2:线程创建的俩种方式
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    innerClass.output1("abcdefghij");
                }
            }
        };

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                innerClass.output1("1234567890");
            }
        });
        thread1.start();
        thread2.start();

    }

    class InnerClass {
        // 线程不安全
        public synchronized void output1(String arg) {
            int length = arg.length();
            for (int i = 0; i < length; i++) {
                System.out.print(arg.charAt(i));
            }
            System.out.println();
        }
    }

    public void output2(String arg) {
        int length = arg.length();
        for (int i = 0; i < length; i++) {
            System.out.print(arg.charAt(i));
        }
        System.out.println();
    }

//    相当于外部类
//    static class OuterClass{
//    }
}

// 相当于静态内部类
class OuterClass {
    public static void output(String arg) {
        int length = arg.length();
        for (int i = 0; i < length; i++) {
            System.out.print(arg.charAt(i));
        }
        System.out.println();
    }
}
