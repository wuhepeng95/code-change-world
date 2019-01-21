package threadtest;

public class Thread1 extends Thread {
    @Override
    public void run() {
        int i = 1;
        while (i < 20) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " is running--" + i);
                i++;
                if (i == 6) {
                    throw new Exception("i=6了抛出new Exception,被catch后，线程未终止");
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