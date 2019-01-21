package threadtest;

public class Thread2 implements Runnable {
    @Override
    public void run() {
        int i = 1;
        while (i < 15) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " is running--" + i);
                i++;
                if (i == 3) {
                    throw new RuntimeException("i=3 抛出RuntimeException，线程终止");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("i=" + i);
        }
    }

    public static void main(String[] args) {
        new Thread2().run();
    }
}
