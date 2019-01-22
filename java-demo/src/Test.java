import java.util.HashMap;
import java.util.Map;

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

        System.currentTimeMillis();

        String s1 = "Hello";
        String s2 = new String("Hello");
        //String的intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池。
        String s3 = s2.intern();
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s3);  // true

        int sum = 1;
        for (int i = 1; i < 10000; i++) {
            sum *= i;
        }
        System.out.println(sum);

        Map integerMap = new HashMap<String, Integer>();
        System.out.printf(String.valueOf(integerMap.get("sdf")));

    }
}
