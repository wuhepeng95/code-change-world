package pattern1;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Demo {
    static int x = 0;
    static int y = 0;

    public static void main(String[] args) throws AWTException {
        Timer timer = new Timer();

        final Robot robot = new Robot();

        TimerTask timerTask = new TimerTask() {
            public void run() {
                System.out.println("正在执行任务...");
                robot.mouseMove(x, y);
                x++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                y++;
            }
        };
        //任务名称（run()），延迟时间（毫秒），周期时间（毫秒）
        timer.schedule(timerTask, 5000, 1000);
        //取消任务
        //timer.cancel();
    }
}
