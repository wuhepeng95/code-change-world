package reflecttest;

import java.lang.reflect.Field;

/**
 * @author wuhepeng
 * @date 2020/2/10
 */
public class IntegerTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Integer a = 1;
        Integer b = 2;
        System.out.println("swap before : a = " + a + ",b = " + b);
        swap(a, b);
        System.out.println("swap after : a = " + a + ",b = " + b);

    }

    public static void swap(Integer a, Integer b) throws NoSuchFieldException, IllegalAccessException {
//        Field field = Integer.class.getDeclaredField("value");
//        field.setAccessible(true);
//        field.set(a,130);
//        field.set(b,129);

        Field value = a.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.setInt(a, 2);
        value.setInt(b, 1);
    }
}

