public class Test {

    public static void main(String[] args) {
        /**
         * 与&(短路与&&)：一假则假
         * 或|(短路或||)：一真则真
         * ^异或：异为真
         */
        //jvm 内部存储编码为Unicode
        System.out.println(System.getProperty("file.encoding"));
        System.err.println("这个线程怎么回事啊!");

        //String的intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池。
        int sum = 1;
        for (int i = 1; i < 100; i++) {
            sum *= i;
        }
        // 溢出之后变成了 0
        System.out.println(sum);
    }
}
