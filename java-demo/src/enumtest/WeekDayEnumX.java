package enumtest;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举类型里面没有方法，也没有属性，只有项
 * Created by wuhp on 2017/11/2.
 */
public enum WeekDayEnumX {
    SUN(0, "星期日", "Sunday"),
    MON(1, "星期一", "Monday"),
    TUE(2, "星期二", "Tuesday"),
    WED(3, "星期三", "Wednesday"),
    THUR(4, "星期四", "Thursday"),
    FRI(5, "星期五", "Friday"),
    SAT(6, "星期六", "Saturday");

    private Integer index;//索引
    private String cn;//中文表示
    private String en;//英文表示

    WeekDayEnumX(Integer index, String cn, String en) {
        this.index = index;
        this.cn = cn;
        this.en = en;
    }

    public static String getCnByIndex(Integer index) {
        WeekDayEnumX[] values = WeekDayEnumX.values();
        for (WeekDayEnumX value : values) {
            if (value.index == index) {
                return value.cn;
            }
        }
        return null;
    }

    public static String getEnByIndex(Integer index) {
        WeekDayEnumX[] values = WeekDayEnumX.values();
        for (WeekDayEnumX value : values) {
            if (value.index == index) {
                return value.en;
            }
        }
        return null;
    }

    /**
     * 获取所有的索引list
     *
     * @return
     */
    public static List<Object> getAllIndex() {
        List<Object> list = new ArrayList<>();
        WeekDayEnumX[] values = WeekDayEnumX.values();
        for (WeekDayEnumX value : values) {
            list.add(value.index);
        }
        return list;
    }

    /**
     * 将枚举值中的所有属性封装到一个list集合中
     *
     * @return
     */
    public static List<List<Object>> getAll() {
        List<List<Object>> lists = new ArrayList<>();
        WeekDayEnumX[] values = WeekDayEnumX.values();
        for (WeekDayEnumX value : values) {
            List<Object> elementList = new ArrayList<>();

            elementList.add(value.name());
            elementList.add(value.index);
            elementList.add(value.cn);
            elementList.add(value.en);

            lists.add(elementList);
        }

        return lists;
    }
}
