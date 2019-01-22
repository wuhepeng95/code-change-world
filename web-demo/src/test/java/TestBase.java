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
        Map<String, Object> param = new HashMap<>();
        param.put("pageSize", 10);
        param.put("startIndex", 1);
        List<WhpTest2> getall = whpTestDao.getall(param);
        Integer count = whpTestDao.count(param);
        System.out.println(count);
        for (WhpTest2 whpTest : getall) {
            System.out.println(whpTest.toString());
        }
    }

    @Test
    public void thread() {
        Map<String, Object> param = new HashMap<>();
        param.put("pageSize", 10);
        param.put("startIndex", 1);

        //创建现场池
        ExecutorService executorService = Executors.newCachedThreadPool();

        // Runnable run 方法返回为空，Callable call 方法返回范型V

        // 原生写法
        Callable<List<WhpTest2>> call1 = new Callable<List<WhpTest2>>() {
            @Override
            public List<WhpTest2> call() throws Exception {
                // 先暂停两秒
                System.out.println("我是" + Thread.currentThread().getName() + "，我暂停了两秒。如果想get()我的值，得先等我执行完return ^.^");
                TimeUnit.SECONDS.sleep(2);
                return whpTestDao.getall(param);
            }
        };

        // lambda最简写法
        Callable<Object> call2 = () -> whpTestDao.count(param);

        // summit后执行 返回结果
        Future<List<WhpTest2>> submit = executorService.submit(call1);
        Future<Object> submit1 = executorService.submit(call2);

        // 输出结果
        try {
            System.out.println("获取查询结果集：");
            submit.get().forEach(whpTest2 -> System.out.println(whpTest2.toString()));
            System.out.println("获取查询结果数量：" + (int) submit1.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
