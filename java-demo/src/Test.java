import org.python.antlr.ast.Str;
import reflecttest.changefieldvalue.TestBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        /**
         * 与&(短路与&&)：一假则假
         * 或|(短路或||)：一真则真
         * ^异或：异为真
         */
        //jvm 内部存储编码为Unicode tomcat默认url编码iso8895-1
        System.out.println(System.getProperty("file.encoding"));
        System.err.println("这个线程怎么回事啊!");
        System.out.println(System.getProperty("file.encoding"));

        //String的intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池。
        int sum = 1;
        for (int i = 1; i < 35; i++) {
            // 溢出之后变成了 0
            if (i == 29 || i == 30 || i == 31 || i == 32 || i == 33 || i == 34) {
                sum = sum * 4;
                System.out.println("i:" + i + ",sum:" + sum);
                System.out.println(Integer.toBinaryString(sum));
            } else {
                sum *= i;
            }
        }

        // java正在取第一个匹配到的参数
        String reg = "8r";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("sdkfh2348y92h32 8r h2r82 3h- j23rji 8r");
        System.out.println(matcher.find());
        System.out.println(matcher.group());

        List<TestBean> ss = new ArrayList<>();
        List<String> collect = ss.stream().map(TestBean::getUrl).collect(Collectors.toList());
        System.out.println(collect);

        String s1s = "ssss 、aaaa";
        String[] array = s1s.split("、");
        for (int i = 0; i < array.length; i++) {
            array[i]=array[i].trim();
        }
        System.out.println(array);
    }

}
