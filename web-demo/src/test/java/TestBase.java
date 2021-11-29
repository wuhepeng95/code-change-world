import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import i.am.whp.bean.MyTable;
import i.am.whp.dao.MyTableDao;
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
    MyTableDao myTableDao;

    @Test
    public void list() {
        // mybatis
//        Map<String, Object> param = new HashMap<>();
//        param.put("pageSize", 10);
//        param.put("startIndex", 0);
//        List<MyTable> getall = myTableDao.getall(param);
//        Integer count = myTableDao.count(param);
//        System.out.println(count);
//        for (MyTable whpTest : getall) {
//            System.out.println(whpTest.toString());
//        }

        //   mybatis-plus
//        System.out.println(myTableDao.insert(new MyTable("2", 2)));
        System.out.println(myTableDao.selectList(new Wrapper<MyTable>() {
            @Override
            public MyTable getEntity() {
                return null;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public void clear() {

            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        }));
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
        Callable<List<MyTable>> call1 = new Callable<List<MyTable>>() {
            @Override
            public List<MyTable> call() throws Exception {
                // 先暂停两秒
                System.out.println("我是" + Thread.currentThread().getName() + "，我暂停了两秒。如果想get()我的值，得先等我执行完return ^.^");
                TimeUnit.SECONDS.sleep(2);
                return myTableDao.getall(param);
            }
        };

        // lambda最简写法
        Callable<Object> call2 = () -> myTableDao.count(param);

        // submit后执行 返回结果
        // 注意 submit之后后吞异常 返回null
        Future<List<MyTable>> submit = executorService.submit(call1);
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
