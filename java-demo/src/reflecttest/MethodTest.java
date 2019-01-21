package reflecttest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wuhp on 2017/11/2.
 */
public class MethodTest {
    public static void main(String[] args) {
        String[] names = {"tom", "tim", "allen", "alice"};
        //Class.forName(classname) 用于做类加载
        //obj.getClass() 用于获得对象的类型
        //类名.class 用于获得指定的类型，传参用
        Class<?> clazz = Test.class;
        try {
            Method method1 = clazz.getMethod("sayHello", Integer.class);
            Method method2 = clazz.getMethod("sayHi", String.class);
            for (String name : names) {
                method1.invoke(clazz.newInstance(), 1);
                method2.invoke(clazz.newInstance(), name);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

class Test {
    public void sayHi(String name) {
        System.out.println("Hi " + name);
    }

    public void sayHello(Integer i) {
        System.out.println("Hello " + i);
    }
}