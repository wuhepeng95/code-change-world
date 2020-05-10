package dateandtimetest;

import java.util.Date;

/**
 * @author wuhepeng
 * @date 2020/5/9
 */
public class TimeBlockTest {
    public static void main(String[] args) {
        Date date = TimeUtil.stringToDate("2020-05-09 07:30:00", TimeUtil.DEFAULT_TIME_FORMAT);
        System.out.println(TimeBlockTest.toBlock(date));
    }

    public static int toBlock(Date date) {
        Date day = TimeUtil.formatDate(date, "yyyy-MM-dd");
        long milliSeconds = date.getTime() - day.getTime();
        long step = 1800000L;
        int blockIndex = (int) ((milliSeconds - 2L * step * 8L) / step);
        return blockIndex;
    }
}
