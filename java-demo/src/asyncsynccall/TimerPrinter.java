package asyncsynccall;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by wuhp on 2018/3/27
 */
public class TimerPrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Date now = new Date();
        System.out.println("Now time is :" + now);
        Toolkit.getDefaultToolkit().beep();
    }
}
