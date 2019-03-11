import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        /**
         * 与&(短路与&&)：一假则假
         * 或|(短路或||)：一真则真
         * ^异或：异为真
         */
        System.err.println("这个线程怎么回事啊!");
        //jvm 内部存储编码为Unicode
        System.out.println(System.getProperty("file.encoding"));

        String s1 = "Hello";
        String s2 = new String("Hello");
        //String的intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池。
        String s3 = s2.intern();
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s3);  // true

        int sum = 1;
        for (int i = 1; i < 100; i++) {
            sum *= i;
        }
        // 溢出之后变成了 0
        System.out.println(sum);

        String asList = "1,3";
        List<String> strings = Collections.singletonList(asList);
        String fiveMethodTypeStr = strings.stream().map(s -> s + ",").collect(Collectors.joining());

        System.out.println(fiveMethodTypeStr.substring(0, fiveMethodTypeStr.lastIndexOf(",")));
        System.out.println(fiveMethodTypeStr.endsWith(",") ? fiveMethodTypeStr.substring(0, fiveMethodTypeStr.length() - 1) : fiveMethodTypeStr);

        String ss = "12345678";
        System.out.println(ss.length());
        // subString是 从beginIndex开始，不包含endIndex
        System.out.println(ss.substring(1, 2));
        System.out.println("v" + (Integer.parseInt("v1.0".substring(1, 2)) + 1) + ".0");

        List<String> listA = new ArrayList<>();
        listA.add("1");
        listA.add("2");

        List<String> listB = new ArrayList<>();
        listB.add("2");
        listB.add("3");

        // 交集
//        listA.retainAll(listB);
//        System.out.println("listA = " + listA);
//        // 差集
//        listA.removeAll(listB);
//        System.out.println("listA = " + listA);
        // 并集
        listA.removeAll(listB);
        listA.addAll(listB);
        System.out.println("listA = " + listA);

    }
}