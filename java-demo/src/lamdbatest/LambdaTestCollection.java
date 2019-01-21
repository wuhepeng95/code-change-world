package lamdbatest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wuhp on 2017/11/1.
 */
public class LambdaTestCollection {
    public static void main(String[] args) {
        /*        List<String> ls = new ArrayList();*/
        List<String> ls = Arrays.asList("李四", "张三", "王二", "zhangsan", "lisi", "wanger");
        /* 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示， 看起来像C++的作用域解析运算符*/
        ls.forEach(System.out::println);
        /*Collections.sort(ls); 正规的比较写法*/
        Collections.sort(ls, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        System.out.println(ls);
        /*简化1：lambda简化*/
        Collections.sort(ls, (String s1, String s2) -> s2.compareTo(s1));
        System.out.println(ls);
        /*简化2：省去类型，自动类型推导*/
        Collections.sort(ls, (s1, s2) -> s1.compareTo(s2));
        System.out.println(ls);
        /*简化3:：最简化了*/
        ls.sort(Comparator.reverseOrder());
        System.out.println(ls);

    }
}
