import com.alibaba.fastjson.JSON;
import i.am.whp.mapper.local.MyTableMapper;
import i.am.whp.mapper.local.MyTableMapperImpl;
import i.am.whp.mapper.qingqing.PhoneNumberDeviceRelationMapper;
import i.am.whp.model.MyTable;
import i.am.whp.service.MyService;
import i.am.whp.test.MyBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class CommonTest extends SpringBootTestBase {

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
    @Autowired
    MyTableMapper myTableMapperImpl;

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
    public void redis() {
        System.out.println(redisTemplate.hasKey("name"));
    }

    @Test
    public void mybatisReturnCountMap() {
        Map<Integer, Integer> countByStatus = myTableMapperImpl.countByStatus();
        System.out.println(JSON.toJSONString(countByStatus));
    }
}
