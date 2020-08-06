import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 与&(短路与&&)：一假则假
         * 或|(短路或||)：一真则真
         * ^异或：异为真
         */
        // jvm 内部存储编码为Unicode tomcat默认url编码iso8895-1
        System.out.println(System.getProperty("file.encoding"));
        // 系统变量↓ jvm运行时环境变量↑
//        System.out.println(System.getenv());
        System.err.println("这个线程怎么回事啊!");
        // String的intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池。

        // java正在取第一个匹配到的参数
        String reg = "^<[^>^<]*>$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("胜多负少生<不知道222>巅峰大是大非<不知道><你好啊>");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        String regexp = "^[\u4F18|\u826F|\u4E2D|\u5DEE][+|\\-]?$";

        // list里的string拼接
        List<String> stringList = Arrays.asList("A", "B", "C");
        StringBuilder ss = new StringBuilder();
        stringList.forEach(s -> ss.append(s).append(":"));
        System.out.println(ss);

        String concat = stringList.stream().reduce("", (s1, str) -> s1.concat(str.toLowerCase() + ":"));
        System.out.println(concat);

        String concat2 = stringList.stream().map(s -> s.toLowerCase()).collect(Collectors.joining(":"));
        System.out.println(concat2);

        System.out.println(String.join(":", stringList));

        int[] data = {1, 2, 3, 4, 5};
        List list = Arrays.asList(data);
        System.out.println("列表中的元素数量是：" + list.size());

        System.out.println("核心线程数(CPU核数):" + Runtime.getRuntime().availableProcessors());
    }
}
