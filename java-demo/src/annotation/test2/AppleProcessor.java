package annotation.test2;

import java.lang.reflect.Field;

public class AppleProcessor {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("annotation.test2.Apple");
            Field[] fields = cls.getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(FruitColor.class)) {
                    FruitColor color = field.getAnnotation(FruitColor.class);
                    System.out.println("Apple Color:" + color.fruitColor());
                }
                if (field.isAnnotationPresent(AppleSize.class)) {
                    AppleSize size = field.getAnnotation(AppleSize.class);
                    System.out.println("Apple Size:" + size.size());
                }
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
