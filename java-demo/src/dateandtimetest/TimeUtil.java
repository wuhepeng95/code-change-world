package dateandtimetest;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {
    public final static long HALF_AN_HOUR_IN_MINISECONDS = 60L * 60 * 1000;
    public final static long HOUR_IN_MINISECONDS = 60L * 60 * 1000;
    public final static int HOUR_IN_SECOND = 60 * 60;
    public final static long DAY_IN_MINISECONDS = 24L * 60 * 60 * 1000;
    public final static long WEEK_IN_MINISECONDS = 7L * 24 * 60 * 60 * 1000;
    public final static long LEAP_YEAR_IN_MINISECONDS = 366L * 24 * 60 * 60 * 1000;

    public static final String DATE_TO_YEAR_MONTH = "yyyy-MM";
    public static final String DATE_TO_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_CHINA_TIME_FORMAT = "yyyy年MM月dd日 HH时mm分";
    public static final String TIME_IN_DETAIL_STRING_FORMAT = "yyyyMMddHHmmss";

    private static final String DATA_FORMAT_MONTH_DAY = "M月d日";
    public static final String TIME_FORMAT_IN_DAY = "HH:mm";

    private static final String DATA_FORMAT_MONTH_DAY_TIME = "M月d日 HH:mm";

    public static final Date SYSTEM_END_TIME = stringToDate("2099-12-30");

    /**
     * 1970 年至今的秒数
     */
    public static long getCurrentTimeSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static Date getMinDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, 1, 1, 0, 0);
        return calendar.getTime();
    }

    public static Timestamp getMinDateTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, 1, 1, 0, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static int getChinaeseDayInWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY) {
            return 7;
        } else {
            return dayOfWeek - 1;
        }
    }

    public static String timeStampToDefaultTimeInDetailString(Date time) {
        return timestampToString(time, TIME_IN_DETAIL_STRING_FORMAT);
    }

    public static Timestamp defaultTimeInDetailStringToTimeStamp(String dateStr) {
        return stringToTimeStamp(dateStr, TIME_IN_DETAIL_STRING_FORMAT);
    }

    public static String timeStampToMonthDayTime(Date time) {
        return timestampToString(time, DATA_FORMAT_MONTH_DAY_TIME);
    }

    public static String timeStampToMonthDay(Date time) {
        return timestampToString(time, DATA_FORMAT_MONTH_DAY);
    }

    public static String dateToTimeInDay(Date time) {
        return dateToString(time, TIME_FORMAT_IN_DAY);
    }

    public static String dateToMonthDay(Date time) {
        return dateToString(time, DATA_FORMAT_MONTH_DAY);
    }

    public static String timeStampToYearMonthDay(Timestamp time) {
        return timestampToString(time, DATE_TO_YEAR_MONTH_DAY);
    }

    public static Timestamp getTodayStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getTodayEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getStartTimeOfOneDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getStartTimeOfNextDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getEndTimeOfOneDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getStartTimeOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getStartTimeOfNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return getStartTimeOfMonth(cal.getTime());
    }

    public static Timestamp getEndTimeOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getStartTimeOfLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getEndTimeOfLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Date getTodayEndDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return new Date(cal.getTimeInMillis());
    }


    public static Date getTodayStartDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Date(cal.getTimeInMillis());
    }

    public static Timestamp getTomorrowDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getTheDayAfterTomorrowDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 2);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getNextTwoWeekDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 14);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getTheDayBeforeYesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, -2);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getThreeDayAgoDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, -3);
        long time = calendar.getTimeInMillis();
        Timestamp tp = new Timestamp(time);
        return tp;
    }

    /**
     * 获取与今天相差XX天的日期, servalDays为正表示今天以后的时间，未负表示今天之前的时间
     */
    public static Timestamp getServalDaysDate(int servalDays) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, servalDays);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 获取与指定日期相差XX天的日期, servalDays为正表示今天以后的时间，未负表示今天之前的时间
     */
    public static Timestamp getServalDaysDate(Date time, Integer servalDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_MONTH, servalDays);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 周一为1， 周日为7，依次类推
     */
    public static int getChineseDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return 7;
        } else {
            return dayOfWeek - 1;
        }
    }

    public static int getChineseDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return 7;
        } else {
            return dayOfWeek - 1;
        }
    }

    public static String timestampToString(Timestamp time) {
        return timestampToString(time, DEFAULT_TIME_FORMAT);
    }

    public static String getChineseTimeString() {
        return timestampToString(getCurrentTimestamp(), DEFAULT_CHINA_TIME_FORMAT);
    }

    public static String timestampToString(Date time, String formart) {
        DateFormat sdf = new SimpleDateFormat(formart);
        if (null == time) {
            return "";
        }
        return sdf.format(time);
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date == null) {
            return "";
        }
        return sdf.format(date);
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        if (date == null) {
            return "";
        }
        return sdf.format(date);
    }

    public static Date formatDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String formatedDate = sdf.format(date);
        return stringToDate(formatedDate, format);
    }

    /**
     * 日期加减 date格式为：YYYY-MM-DD dayLength传负数，日期往前，dayLength传正数，日期往后
     */
    public static String dayAfter(String date, int dayLength) {
        try {
            int year = new Integer(date.substring(0, 4)).intValue();
            int month = new Integer(date.substring(5, 7)).intValue();
            int day = new Integer(date.substring(8, 10)).intValue();
            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, day);
            cal.add(Calendar.DATE, dayLength);
            String monthStr = String.valueOf(cal.get(Calendar.MONTH) + 1);
            String dayStr = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
            return cal.get(Calendar.YEAR) + "-" + (monthStr.length() > 1 ? monthStr : "0" + monthStr) + "-"
                    + (dayStr.length() > 1 ? dayStr : "0" + dayStr);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static Date dayAfter(Date date, int dayLength) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, dayLength);
        return cal.getTime();
    }

    public static Date hourAfter(Date date, int hourLength) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hourLength);
        return cal.getTime();
    }

    public static Date hourBefore(Date date, int hourLength) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, -1 * hourLength);
        return cal.getTime();
    }

    public static Date monthAfter(Date date, int monthLength) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthLength);
        return cal.getTime();
    }

    public static Timestamp monthTimestampAfter(Date date, int monthLength) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthLength);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp stringToTimeStamp(String date, String formart) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formart);
        Date dateTime = null;
        try {
            dateTime = dateFormat.parse(date);
        } catch (ParseException e) {
        }
        return new Timestamp(dateTime.getTime());
    }

    public static Date stringToDate(String dateStr, String dateFormatDate) {
        SimpleDateFormat shortDateFormat = new SimpleDateFormat(dateFormatDate);
        try {
            return shortDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    public static Date stringToDate(String dateStr) {
        Date date = null;
        SimpleDateFormat shortDateFormat = new SimpleDateFormat(DATE_TO_YEAR_MONTH_DAY);
        try {
            date = shortDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * 当前日期比较 当前日期大于传入日期返回true 当前日期小于等于传入日期返回false
     */
    public static boolean currentDateCompare(Date dateStr) {
        if (dateStr == null) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentdate = new Date();
        String currentdateStr = format.format(currentdate);
        Date currentFormatDate = null;
        try {
            currentFormatDate = format.parse(currentdateStr);
            if (dateStr.getTime() >= currentFormatDate.getTime()) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description 获取指定时间与当前时间的间隔，单位为秒
     * 指定时间在当前时间之后， 返回正值
     * 指定时间在当前时间之前， 返回负值
     */
    public static long getIntervalMiniSeconds(Date courseStartTime) {
        long intervalMills = courseStartTime.getTime() - System.currentTimeMillis();
        return intervalMills / (1000);
    }

    /**
     * @description 获取指定时间与当前时间的间隔，单位为分钟
     * 指定时间在当前时间之后， 返回正值
     * 指定时间在当前时间之前， 返回负值
     */
    public static long getIntervalMins(Timestamp courseStartTime) {
        long intervalMills = courseStartTime.getTime() - System.currentTimeMillis();
        return intervalMills / (1000 * 60);
    }

    private static boolean isExpire(Timestamp time, int validDays) {
        if (validDays <= 0) {
        }
        long requestTime = time.getTime();
        long currentTime = System.currentTimeMillis();
        return (!((currentTime > requestTime) && (currentTime - requestTime < (validDays * 24 * 60 * 60 * 1000L))));
    }

    /**
     * 是否处于有效期内， 有效期为一天
     */
    public static boolean isExpireInOneDay(Timestamp time) {
        return isExpire(time, 1);
    }

    /**
     * 获取当前时间的下一小时
     */
    public static Timestamp getNextHour() {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime + HOUR_IN_MINISECONDS);
    }

    /**
     * 获取当前时间的下一小时
     */
    public static Date getUnixStartTime() {
        return new Date(0);
    }

    /**
     * 获取当前时间
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * Date转timestamp
     */
    public static final Timestamp fromDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return new Timestamp(date.getTime());
        }
    }

    public static final long getTime(Date date, long defaultValue) {
        if (null == date) {
            return defaultValue;
        }

        return date.getTime();
    }

    public static final boolean isSameDay(Date time1, Date time2) {
        return (time1.getTime() / DAY_IN_MINISECONDS) == (time2.getTime() / DAY_IN_MINISECONDS);
    }

    public static final boolean isSameDayOfGMT(Date time1, Date time2) {
        return ((time1.getTime() + 8L * HOUR_IN_MINISECONDS) / DAY_IN_MINISECONDS) == ((time2.getTime() + 8L * HOUR_IN_MINISECONDS) / DAY_IN_MINISECONDS);
    }

    public static final boolean isSameDayWithSameDelay(Date time1, Date time2, int delays, TimeUnit timeUnit) {
        Date time1New = new Date(time1.getTime() + timeUnit.toMillis(delays));
        Date time2New = new Date(time2.getTime() + timeUnit.toMillis(delays));
        return isSameDay(time1New, time2New);
    }

    public static Date getDateFromStr(String timeStr, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(timeStr);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public static void main(String argc[]) {
        System.out.println(getTimeBeforeSeconds(600));
//		System.out.println(System.currentTimeMillis());
//		System.out.println(getCurrentTimeSeconds());
//		System.out.println(getHours(1, 2));
//
////		System.out.println(getTomorrowDate());
//		System.out.println(getTheDayAfterTomorrowDate());
////		System.out.println(getTodayStartTime());
////		System.out.println(timeStampToMonthDayTime(getCurrentTimestamp()));
////		System.out.println(timeStampToMonthDay(getCurrentTimestamp()));
//		System.out.println(timeStampToYearMonthDay(getServalDaysDate(30)));
//		System.out.println(timeStampToYearMonthDay(getServalDaysDate(365)));
//		System.out.println(timeStampToYearMonthDay(getServalDaysDate(-30)));
//		System.out.println(timeStampToYearMonthDay(getServalDaysDate(-365)));
//		System.out.println(timeStampToYearMonthDay(getServalDaysDate(new Date(), -365)));
//
//		System.out.println(getChinaeseDayInWeek(stringToDate("2015-11-16", "yyyy-MM-dd")));
//		System.out.println(getChinaeseDayInWeek(stringToDate("2015-11-15", "yyyy-MM-dd")));
//
//
//		System.out.println(getStartTimeOfOneDay(new Date()));
//		System.out.println(getEndTimeOfOneDay(new Date()));
//
//		System.out.println(getUnixStartTime());
//
//		System.out.println(monthAfter(new Date(), 1));
//		System.out.println(monthAfter(new Date(), -1));
//		System.out.println(monthAfter(new Date(), 12));
//		System.out.println(getMonthOfYear(new Date()));
//
//		System.out.println(timeStampToDefaultTimeInDetailString(new Date()));
//
//		System.out.println(dateToString(getStartTimeOfMonth(new Date()), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(dateToString(getEndTimeOfMonth(new Date()), "yyyy-MM-dd HH:mm:ss"));
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.DAY_OF_MONTH,-1);
//		System.out.println(dateToString(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));

//		System.out.println(dateToString(getStartTimeOfNextMonth(new Timestamp(new Date(2016, 11, 31).getTime())), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(dateToString(getStartTimeOfMonth(new Timestamp(new Date(2016, 11, 31).getTime())), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(dateToString(getMondayDateOfNextWeek(new Timestamp(new Date(2016, 11, 31).getTime())), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(dateToString(getMondayDateOfThisWeek(new Timestamp(new Date(2016, 11, 31).getTime())), "yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * start from zero
     */
    public static int getMonthOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static int getDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public static double getHours(int startBlock, int endBlock) {
        return (endBlock - startBlock + 1) / 2.0;
    }

    /**
     * 获取某个时间间隔几分钟的时间，负数为之前
     *
     * @param timestamp
     * @param minute
     * @return Timestamp
     */
    public static Timestamp getMinuteAfterNow(Timestamp timestamp, int minute) {
        if (null == timestamp) {
            return null;
        }
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(timestamp);
        Cal.add(Calendar.MINUTE, minute);
        return new Timestamp(Cal.getTime().getTime());
    }

    /**
     * 比较指定时间是否在指定时间区间内
     *
     * @param startTime
     * @param endTime
     * @param toCompareTime
     * @return
     */
    public static boolean compareTime(Date startTime, Date endTime, Date toCompareTime) {
        if (toCompareTime == null) {
            return false;
        }
        if (startTime != null && endTime != null) {
            return toCompareTime.getTime() >= startTime.getTime() && toCompareTime.getTime() <= endTime.getTime();
        } else if (startTime != null && endTime == null) {
            return toCompareTime.getTime() >= startTime.getTime();
        } else {
            return toCompareTime.getTime() <= endTime.getTime();
        }
    }

    /**
     * 获得本周一0点时间，每周开始为周一
     *
     * @param date
     * @return
     */
    public static Timestamp getMondayDateOfThisWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 1);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getMondayDateOfNextWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        return getMondayDateOfThisWeek(cal.getTime());
    }

    /**
     * 获得本周日24点时间，每周开始为周一
     *
     * @param date
     * @return
     */
    public static Timestamp getSundayDateOfThisWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 7);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 获得指定小时后时间，正为之后，负为之前
     *
     * @param date
     * @param hour
     * @return
     */
    public static Timestamp getHourAfterDate(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static long getTimeInMillis(Date date) {
        return date == null ? 0 : date.getTime();
    }

    /**
     * 如果当前时间在21点~次日9点，则返回次日9点，否则返回null
     *
     * @return
     */
    public static Date getDelaySendTime21to9() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 21 || hour <= 8) {
            if (hour >= 21) {
                cal.add(Calendar.DAY_OF_YEAR, 1);
            }
            cal.set(Calendar.HOUR_OF_DAY, 8);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
        }
        return null;
    }

    /***
     *获取两个时间点之间相差多说分钟
     */
    public static long getDuringMinsOfTwoTimestamp(Timestamp time0, Timestamp time1) {
        long duringTime = time1.getTime() - time0.getTime();
        if (duringTime < 0) {
            duringTime = -duringTime;
        }
        return duringTime / (60 * 1000);
    }

    public static Timestamp getTimeStamp(long time) {
        return new Timestamp(time);
    }

    public static Timestamp getTimeBeforeSeconds(int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0 - seconds);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param fromDate
     * @param toDate
     * @return {天, 时, 分, 秒}
     */
    public static long[] getIntervalTime(Date fromDate, Date toDate) {
        if (fromDate == null || toDate == null) {
            return null;
        }
        long intervalMills = toDate.getTime() - fromDate.getTime();
        long day = intervalMills / (24L * 60 * 60 * 1000);
        long hour = (intervalMills / (60L * 60 * 1000) - day * 24);
        long min = ((intervalMills / (60L * 1000)) - day * 24 * 60 - hour * 60);
        long sec = (intervalMills / 1000 - (day * 24 * 60 * 60) - (hour * 60 * 60) - (min * 60));
        return new long[]{day, hour, min, sec};
    }
}
