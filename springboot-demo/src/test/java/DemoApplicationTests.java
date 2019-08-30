import com.alibaba.fastjson.JSON;
import i.am.whp.DemoApplication;
import i.am.whp.mapper.aws.MyTableMapper;
import i.am.whp.mapper.qingqing.PhoneNumberDeviceRelationMapper;
import i.am.whp.service.MyService;
import i.am.whp.test.MyBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    MyTableMapper myTableMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        System.out.println(myService.hi());
    }

    @Test
    public void setProfile() {
        System.out.println(myBean.getName());
    }

    @Test
    public void testDaoQingqing() {
        System.out.println(JSON.toJSONString(phoneNumberDeviceRelationMapper.getById(1)));
    }

    @Test
    public void testDaoAws() {
        System.out.println(JSON.toJSONString(myTableMapper.getById(1)));
    }

    @Test
    public void jedis() {
        System.out.println(redisTemplate.hasKey("name"));
    }
}
