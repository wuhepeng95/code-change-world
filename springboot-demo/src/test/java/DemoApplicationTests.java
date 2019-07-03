import com.alibaba.fastjson.JSON;
import i.am.whp.DemoApplication;
import i.am.whp.mapper.PhoneNumberDeviceRelationMapper;
import i.am.whp.test.MyBean;
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
    @Autowired
    PhoneNumberDeviceRelationMapper phoneNumberDeviceRelationMapper;

    @Test
    public void contextLoads() {
        System.out.println(myService.hi());
    }

    @Test
    public void setProfile() {
        System.out.println(myBean.getName());
    }

    @Test
    public void testDao() {
        System.out.println(JSON.toJSONString(phoneNumberDeviceRelationMapper.getById(1)));
    }
}
