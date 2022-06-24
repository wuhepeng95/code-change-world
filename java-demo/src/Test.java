import cn.hutool.core.util.PinyinUtil;
import com.alibaba.fastjson.JSON;
import static java.util.stream.Collectors.toMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import reflecttest.changefieldvalue.TestBean;
import reflecttest.changefieldvalue.TestBeanDto;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
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
        String reg2 = "^<[^>^<]*>$";
        String reg = "^[a-zA-Z]+[a-zA-Z0-9_]*$";
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

        // String的三种连接方式
        String concat = stringList.stream().reduce("", (s1, str) -> s1.concat(str.toLowerCase() + ":"));
        System.out.println(concat);
        String concat2 = stringList.stream().map(s -> s.toLowerCase()).collect(Collectors.joining(":"));
        System.out.println(concat2);
        System.out.println(String.join(":", stringList));

        // Arrays.asList慎用
        int[] data = {1, 2, 3, 4, 5};
        List list = Arrays.asList(data);
        System.out.println("列表中的元素数量是：" + list.size());

        System.out.println("核心线程数(CPU核数):" + Runtime.getRuntime().availableProcessors());

        // 集合size慎用
        Set<Long> agentIdS = new HashSet<>();
        agentIdS.add(null);
        System.out.println(agentIdS.size());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(null);
        System.out.println(arrayList.size());

        // 对象与引用问题 注意原map也变了
        Map<String, String> map = new HashMap<>();
        map.put("2", "2");
        Map<String, String> map2 = map;//redundant冗余
        map2.put("1", "1");
        System.out.println(map);
        System.out.println(map2);

        // blank理解为空格 empty理解为空白
        String bl = " ";
        String bl2 = "";
        System.out.println(StringUtils.isBlank(bl));// true
        System.out.println(StringUtils.isEmpty(bl));// false
        System.out.println(StringUtils.isBlank(bl2));// true
        System.out.println(StringUtils.isEmpty(bl2));// true

        TestBeanDto dto = new TestBeanDto();
        List<TestBean> testBeans = new ArrayList<>();
        TestBean testBean = new TestBean();
        testBean.setUrl("323");
        testBeans.add(testBean);
        dto.setTestBeans(testBeans);
        System.out.println(JSON.toJSONString(dto));

//        System.out.println(testBeans.stream().collect(toMap(TestBean::getId, testBean1 -> Optional.ofNullable(testBean1.getLongList()).orElse(null))));

        for (TestBean bean : dto.getTestBeans()) {
            bean.setId(1212);
        }
        System.out.println(JSON.toJSONString(dto));

        Map<Long, List<Long>> listMap = new HashMap<>();
        listMap.put(1L, null);
        System.out.println(listMap);


        TestBean testBean1 = new TestBean();
        new Test().setField(testBean1, "url", "wwwwwwwww");
        System.out.println(testBean1);


            List<TestBean> lines = new ArrayList<>();
        TestBean e = new TestBean();
        e.setId(1);
            lines.add(e);
            List<Integer> itemIdList = lines.stream().filter(testBean2 -> testBean2.getId() != null).map(TestBean::getId).distinct().collect(Collectors.toList());
            System.out.println(itemIdList);
            System.out.println(CollectionUtils.isNotEmpty(itemIdList));

        String itemIds = lines.stream().map(TestBean::getId).distinct().map(Object::toString).collect(Collectors.joining(","));
        System.out.println(itemIds);

            List<Long> longs = new ArrayList<>();
            longs.add(1L);
        if (longs.size() == 1) {
            longs.add(longs.get(0));
        }
            System.out.println(longs);


        System.out.println(PinyinUtil.getPinYin("你好"));

        BigDecimal bigDecimal = new BigDecimal("-100", MathContext.DECIMAL64);
        System.out.println(bigDecimal.abs());


        System.out.println(new Date());
    }

    public void setField(TestBean bean, String fieldName, String value){
        bean.setUrl("hhhhh");

        Class<? extends TestBean> clazz = bean.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            System.out.println(field.get(bean));
            field.set(bean, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
