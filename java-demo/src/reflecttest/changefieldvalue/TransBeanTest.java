package reflecttest.changefieldvalue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by whp on 2019-03-05
 */
public class TransBeanTest<T> {

    String replaceUrl = "";
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(TransBeanTest.class.getResourceAsStream("reflecttest.changefieldvalue.TransBeanTest.class"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void transBean(T t) {
        System.out.println(t);
    }

    public void transList(List<T> list) {
        for (T t : list) {
            for (Field field : t.getClass().getDeclaredFields()) {
                if (field.getName().contains("url")) {
                    try {
                        Field declaredField = t.getClass().getDeclaredField(field.getName());
                        // 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
                        declaredField.setAccessible(true);
                        declaredField.set(t, "www.souhu.com");
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public void transMap(Map tMap) {

    }

    public static void main(String[] args) {
        List<TestBean> list = new ArrayList<>();
        TestBean bean1 = new TestBean();
        bean1.setId(1);
        bean1.setUrl("www.baidu.com");

        TestBean bean2 = new TestBean();
        bean2.setId(2);
        bean2.setUrl("www.baidu.com");

        list.add(bean1);
        list.add(bean2);

        TransBeanTest<TestBean> transBeanUrl = new TransBeanTest<>();
        transBeanUrl.transList(list);

        for (TestBean testBean : list) {
            System.out.println(testBean.toString());
        }
    }
}
