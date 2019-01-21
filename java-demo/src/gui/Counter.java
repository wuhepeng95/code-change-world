package gui;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 倒计时
 */
public class Counter {

    private JFrame frame;
    private JLabel jl0;

    public static void main(String[] args) throws ParseException {

        new Counter().getTime("2018-09-20 21:06:00");

    }

    /* String -> Date */
    private Date String2Date(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* 倒计时的主要代码块 */
    private void getTime(String dateStr) {

        Date end = String2Date(dateStr);

        long time = (end.getTime() - 1 - new Date().getTime()) / 1000; // 自定义倒计时时间
        long hour = 0;
        long minute = 0;
        long seconds = 0;

        while (time >= 0) {
            hour = time / 3600;
            minute = (time - hour * 3600) / 60;
            seconds = time - hour * 3600 - minute * 60;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><br>距离").append(dateStr).append("还有<br><br>")
                    .append(hour).append("时 ").append(minute).append("分 ").append(seconds).append("秒 ")
                    .append("</html>");
            jl0.setText(stringBuilder.toString());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
        }

    }

    /* 构造 实现界面的开发 GUI */
    public Counter() {
        frame = new JFrame("倒计时");
        jl0 = new JLabel();

        init();

    }

    /* 组件的装配 */
    private void init() {
        JPanel jp = new JPanel();
        jp.add(jl0);

        frame.add(jp);

        frame.setVisible(true);
        frame.setLocation(300, 400);
        frame.setSize(330, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
