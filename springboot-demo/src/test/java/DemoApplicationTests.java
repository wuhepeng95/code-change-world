import i.am.whp.DemoApplication;
import i.am.whp.model.MyBean;
import i.am.whp.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

    @Autowired
    MyService myService;
    @Autowired
    MyBean myBean;

    @Test
    public void contextLoads() {
        myService.hi();
    }

    @Test
    public void setProfile() {
        System.out.println(myBean.getName());
    }
}