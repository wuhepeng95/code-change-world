package asyncsynccall;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by wuhp on 2018/3/27
 */
public class TimerTest {
    public static void main(String[] args) {
        ActionListener listener = new TimerPrinter();
        Timer timer = new Timer(10000, listener);
        timer.start();
        JOptionPane.showMessageDialog(null, "quit");
        System.exit(0);
    }
}
