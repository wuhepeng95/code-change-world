package annotation.test1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//使用反射机制处理自定义注解
public class MyMessageProcessor {
    public static void main(String[] args) {

        try {
            //加载annotationTest.class类
            Class clazz = Class.forName("annotation.test1.AnnotationTest");
            //获取属性
            Field[] fields = clazz.getDeclaredFields();
            //遍历属性
            for (Field field : fields) {
                MyMessage myMessage = field.getAnnotation(MyMessage.class);
                System.out.println("全是注解里默认的：" + "name:" + myMessage.name() + " num:" + myMessage.num() + " desc:" + myMessage.desc());
            }
            //获取类中的方法
            Method[] methods = clazz.getMethods();
            //遍历方法
            for (Method method : methods) {
                //判断方法是否带有MyMessage注解
                if (method.isAnnotationPresent(MyMessage.class)) {
                    // 获取所有注解 method.getDeclaredAnnotations();
                    // 获取MyMessage注解
                    MyMessage myMessage = method.getAnnotation(MyMessage.class);
                    System.out.println("全是注解上自定义的：" + "name:" + myMessage.name() + " num:" + myMessage.num() + " desc:" + myMessage.desc());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
