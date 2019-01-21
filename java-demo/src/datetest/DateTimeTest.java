package datetest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 如果是 JDK8 的应用，可以使用 Instant代替 Date，LocalDateTime代替 Calendar， DateTimeFormatter代替 SimpleDateFormat，
 * 官方给出的解释： simple beautiful strong immutable thread-safe。
 */
public class DateTimeTest {
    public static void main(String[] args) {

        //1.8新增的LocalDateTime 真心不好用啊
        LocalDateTime now = LocalDateTime.now();
        //ISO_DATE_TIME:2018-01-29T11:36:50.954
        now.format(DateTimeFormatter.ISO_WEEK_DATE);
        System.out.println(now);

        //pattern 模式; 图案; 花样，样品; 榜样，典范;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH-mm-ss:sss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);

        Calendar c = Calendar.getInstance();
        c.set(1995, Calendar.SEPTEMBER,29);
        System.out.println(c.getFirstDayOfWeek());

        // 初始化 Date 对象
        Date date = new Date();

        //c的使用
        System.out.printf("全部日期和时间信息：%tc%n",date);
        //f的使用
        System.out.printf("年-月-日格式：%tF%n",date);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n",date);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR",date);
    }
}
