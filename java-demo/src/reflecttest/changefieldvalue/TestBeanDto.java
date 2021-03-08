package reflecttest.changefieldvalue;

import java.util.List;

public class TestBeanDto {
    List<TestBean> testBeans;

    public List<TestBean> getTestBeans() {
        return testBeans;
    }

    public void setTestBeans(List<TestBean> testBeans) {
        this.testBeans = testBeans;
    }
}
