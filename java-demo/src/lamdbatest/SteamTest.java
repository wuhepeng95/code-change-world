package lamdbatest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *()->{}替换匿名类
 * :: 静态方法引用
 */
public class SteamTest {
    public static void main(String[] args) throws Exception {
        /**
         * filter(条件) 过滤
         */
        List<Integer> list = Arrays.asList(1, 1, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("使用filter过滤小于4的值"+list.stream().filter(integer -> integer < 4).collect(Collectors.toList()));
        /**
         * map()集体转换
         */
        System.out.println("使用map集体操作"+list.stream().map(i -> String.valueOf(i).concat("添加内容")).collect(Collectors.toList()));

        /**
         * distinct() 去重
         */
        System.out.println("使用distinct去重"+list.stream().distinct().collect(Collectors.toList()));
        /**
         * iterate() 重复迭代
         * limit(数字) 限定最大长度
         */
        List<Integer> limit = Stream.iterate(1, item -> item + 1).limit(10).collect(Collectors.toList());
        System.out.println(limit);
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::print);
        /**
         * reduce() 折叠 /collect() 汇聚
         */
        List<Integer> ints = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("ints sum is:" + ints.stream().reduce(0, (sum, item) -> sum + item));
        System.out.println(ints.stream().reduce(0, (i, s) -> i * s));/* 字符串连接，concat = "ABCD"*/
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);/* 求最小值，minValue = -3.0*/
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);/* 求和，sumValue = 10, 有起始值*/
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);/* 求和，sumValue = 10, 无起始值*/
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();/* 过滤，字符串连接，concat = "ace"*/
        concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
    }
}
