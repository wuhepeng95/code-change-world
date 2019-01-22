import i.am.whp.bean.WhpTest;
import i.am.whp.bean.WhpTest2;
import i.am.whp.dao.WhpTestDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestBase {
    @Autowired
    WhpTestDao whpTestDao;

    @Test
    public void list() {
        Map<String,Object> param = new HashMap<>();
        param.put("pageSize",10);
        param.put("startIndex",1);
        List<WhpTest2> getall = whpTestDao.getall(param);
        Integer count = whpTestDao.count(param);
        System.out.println(count);
        for (WhpTest2 whpTest : getall) {
            System.out.println(whpTest.toString());
        }
    }

    @Test
    public void thread() {
        Map<String,Object> param = new HashMap<>();
        param.put("pageSize",10);
        param.put("startIndex",1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<Object> call1 = () -> whpTestDao.getall(param);
        Callable<Object> call2 = () -> whpTestDao.count(param);
        executorService.submit(call1);
        executorService.submit(call2);
    }
}
