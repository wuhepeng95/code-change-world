import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleTests {
    @Test
    public void arrayToJson() {
        List<Bean> lists = new ArrayList<>();
        Bean bean = new Bean();
        bean.setS1("111");
        bean.setS2("2222");
        lists.add(bean);

        Bean bean1 = new Bean();
        bean1.setS1("111111111111");
        bean1.setS2("2222222222222");
        lists.add(bean1);
        System.out.println(JSONObject.toJSONString(lists));


    }
    class Bean{
        private String s1;
        private String s2;

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }
    }
}
