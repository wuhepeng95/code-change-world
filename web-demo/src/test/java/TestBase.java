import i.am.whp.bean.WhpTest;
import i.am.whp.dao.WhpTestDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestBase {
    @Autowired
    WhpTestDao whpTestDao;

    @Test
    public void list() {
        Map<String,Object> param = new HashMap<>();
        List<WhpTest> getall = whpTestDao.getall(param);
        Integer count = whpTestDao.count(param);
        System.out.println(count);
        System.out.println(getall);
    }
}
