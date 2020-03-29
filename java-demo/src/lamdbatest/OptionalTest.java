package lamdbatest;

import reflecttest.changefieldvalue.TestBean;

import java.util.Optional;

/**
 * @author wuhepeng
 * @date 2020/3/27
 */
public class OptionalTest {
    public static void main(String[] args) {
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
    }
}
