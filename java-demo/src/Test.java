import com.alibaba.fastjson.JSON;
import reflecttest.changefieldvalue.TestBean;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        System.out.println(1 * 1.0 / 3L);

        System.out.println(UUID.randomUUID());

        Map<Long, String> regionIdNameMap = new LinkedHashMap<>();
        regionIdNameMap.put(1L, "123");
        regionIdNameMap.put(2L, "123");
        System.out.println(JSON.toJSONString(regionIdNameMap));

        regionIdNameMap.clear();
        System.out.println(regionIdNameMap);

        // 不好用的optional
        TestBean testBean = null;
        TestBean testBean1 = new TestBean();
        testBean1.setId(1);
        testBean1.setUrl(null);

        // testBean1替换testBean
        TestBean testBean2 = Optional.ofNullable(testBean).orElse(testBean1);
        String defaultUrl = Optional
                .of(testBean2)
                .map(TestBean::getUrl)
                .orElse("default");
        System.out.println(defaultUrl);
        System.out.println("原值未变" + testBean2.getUrl());

        HashSet treeSet = new HashSet();
        treeSet.add("one");
        treeSet.add("two");
        treeSet.add("two");
        treeSet.add("three");
        treeSet.add("four");
        treeSet.add("four");
        treeSet.add("five");
        System.out.println(treeSet);

        List<Bean> beanList = new ArrayList<>();
        beanList.add(new Bean(12,12));

        beanList.remove(new Bean(12,12));
        System.out.println(beanList);
    }

    static class Bean{
        private long teacherId;
        private long userId;

        public Bean(long teacherId, long userId) {
            this.teacherId = teacherId;
            this.userId = userId;
        }
    }

}
