package reflecttest;

import java.lang.reflect.Field;

/**
 * Created by wuhp on 2017/11/2.
 */
public class Reflect {
    public static void main(String[] args) {
        //Class<?> clasz = Class.forName("com.fxiaoke.Entity");
        Class<?> clasz = Entity.class;
        Field[] declaredFields = clasz.getDeclaredFields();
        for (Field f : declaredFields) {
            System.out.println(f);

        }
    }
}
