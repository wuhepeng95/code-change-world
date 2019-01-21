package fanxintest.demo1;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ShowTest implements Show<String, Date, List> {
    @Override
    public String show(String s, Date date, List list) {
        System.out.println(s);
        System.out.println(date);
        System.out.println(list);
        return "泛型的测试，很吊的yo";
    }

    public static void main(String[] args) {
        System.out.println(new ShowTest().show("str", new Date(), Arrays.asList("sdfs", "sdfsfsdf")));
    }
}
