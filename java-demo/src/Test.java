import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println(System.getenv());
        System.err.println("这个线程怎么回事啊!");
        // String的intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池。

        // java正在取第一个匹配到的参数
        String reg = "<[^>^<]*>";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("胜多负少生<不知道222>巅峰大是大非<不知道><你好啊>");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
