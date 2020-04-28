
import i.am.whp.model.enums.Status;
import java.util.Date;import com.alibaba.fastjson.JSON;
import i.am.whp.mapper.local.MyTableMapper;
import i.am.whp.mapper.qingqing.PhoneNumberDeviceRelationMapper;
import i.am.whp.model.MyTable;
import i.am.whp.service.MyService;
import i.am.whp.test.MyBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class CommonTest extends SpringBootTestBase {

    @Autowired
    MyService myService;
    @Autowired
    MyBean myBean;
    @Autowired
    PhoneNumberDeviceRelationMapper phoneNumberDeviceRelationMapper;
    /*默认mapper代理类*/
    @Autowired
    MyTableMapper myTableMapper;
    @Autowired
    RedisTemplate redisTemplate;
    /*自定义mapper实现类*/
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

    @Test
    public void currentTest() {
        int threadCount = 100;

        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Random random = new Random();
            MyTable param = new MyTable();
            param.setId(random.nextInt(100));
            param.setName(UUID.randomUUID().toString());
            param.setStatus(Status.valueOf(random.nextInt(3)));
            param.setCreateTime(new Date());

            System.out.println("新增加线程" + i);
            System.out.println("还剩余" + latch.getCount() + "未准备！");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        myService.updateAndInsert(param);
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getId() + "在执行。。。。");
                }
            }).start();
            latch.countDown();
        }
        System.out.println("10个线程已准备完毕，准备开启10个线程的并发~~~");
    }

    @Test
    public void whereTagTest() {
        MyTable param = new MyTable();
        param.setName("11");
        param.setStatus(Status.closed_answer_teacher_open_status);
        myService.whereTest(param);
    }
}
