package gui;

import javax.swing.*;
import java.awt.*;

/**
 * 浮动的字符：线程+GUI
 */
public class FloatChar extends JFrame {
    private static final long serialVersionUID = 1L;

    public FloatChar() {
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setSize(300, 300);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        MyPanel mp = new MyPanel();
        this.add(mp);

        Thread t = new Thread(mp);
        t.start();
    }

    public static void main(String[] args) {
        new FloatChar();
    }

}

class MyPanel extends Panel implements Runnable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int step = 0;

    public MyPanel() {
        this.setBounds(15, 15, 250, 230);
        this.setBackground(Color.black);
    }

    public void paint(Graphics g) {
        g.setColor(new Color((int) ((Math.random() * 255)), (int) ((Math
                .random() * 255)), (int) ((Math.random() * 255))));
        g.drawString("Happy Time !!", 20 + 10 * step, 20 + 10 * step);
    }

    @Override
    public void run() {
        while (true) {
            if (step == 15) {
                step = 0;
            }
            step++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }

    }

}
