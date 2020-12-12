package dateandtimetest;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期常用方法
 */
public class TimeZoneUtils {

    /**
     * 将本地时间（服务器）, 转换成目标时区的时间
     *
     * @param sourceDate
     * @param targetZoneId
     * @return
     */
    public static Date convertTimezone(Date sourceDate, String targetZoneId) {
        return convertTimezone(sourceDate, TimeZone.getDefault(), TimeZone.getTimeZone(targetZoneId));
    }

    /**
     * 将目标时间, 转换成本地（服务器）时区的时间
     *
     * @param sourceDate
     * @param targetZoneId
     * @return
     */
    public static Date convertLocalDate(Date sourceDate, String targetZoneId) {
        return convertTimezone(sourceDate, TimeZone.getTimeZone(targetZoneId), TimeZone.getDefault());
    }

    /**
     * 将sourceDate转换成指定时区的时间
     *
     * @param sourceDate
     * @param sourceTimezone sourceDate所在的时区
     * @param targetTimezone 转化成目标时间所在的时区
     * @return
     */
    public static Date convertTimezone(Date sourceDate, TimeZone sourceTimezone, TimeZone targetTimezone) {

        Calendar calendar = Calendar.getInstance();
        long sourceTime = sourceDate.getTime(); // date.getTime() 为时间戳, 为格林尼治到系统现在的时间差


        calendar.setTimeZone(sourceTimezone);
        calendar.setTimeInMillis(sourceTime);// 设置之后,calendar会计算各种filed对应的值,并保存

        //获取源时区的到UTC的时区差
        int sourceZoneOffset = calendar.get(Calendar.ZONE_OFFSET);

        calendar.setTimeZone(targetTimezone);
        calendar.setTimeInMillis(sourceTime);

        int targetZoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        int targetDaylightOffset = calendar.get(Calendar.DST_OFFSET); // 夏令时


        long targetTime = sourceTime + (targetZoneOffset + targetDaylightOffset) - sourceZoneOffset;

        return new Date(targetTime);
    }

    /**
     * 通过gmt获取timeZone
     *
     * @param gmtTimeZone
     * @return
     */
    public static TimeZone getTimeZoneByGmt(String gmtTimeZone) {
        String offsetId = gmtTimeZone.replace("GMT", "");
        return TimeZone.getTimeZone(ZoneId.ofOffset("GMT", ZoneOffset.of(offsetId)));
    }

    /**
     * 获取当天的最第一豪秒
     *
     * @return
     */
    public static Date getFirstMillSecondDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天的最后一豪秒
     *
     * @return
     */
    public static Date getLastMillSecondDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

}
