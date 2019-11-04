import i.am.whp.lock.ZookeeperLock;
import i.am.whp.lock.mutex.DemoMutex;
import i.am.whp.lock.zookeeper.Client;
import lombok.Builder;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wuhepeng
 * @date 2019/10/16
 */
public class ZookeeperTest extends SpringBootTestBase {

    @Value("${zookeeper.connect.string}")
    String zookeeperConnectString;

    @Resource
    private Client client;

    @Resource(name = "initRedisTemplate")
    RedisTemplate redisTemplate;

    @Test
    public void connectToZk() {
        ZookeeperLock testLock = new ZookeeperLock(zookeeperConnectString, "testLock");
        testLock.lock();
        testLock.unlock();
    }

    @Test
    public void testLock() throws IOException {
        // 模拟高并发
        CountDownLatch countDownLatch = new CountDownLatch(5);

        ArrayList<BuyTickReq> arrayList = new ArrayList();
        arrayList.add(BuyTickReq.builder().usrId(1).buyNum(4).build());
        arrayList.add(BuyTickReq.builder().usrId(2).buyNum(5).build());
        arrayList.add(BuyTickReq.builder().usrId(3).buyNum(8).build());
        arrayList.add(BuyTickReq.builder().usrId(4).buyNum(8).build());
        arrayList.add(BuyTickReq.builder().usrId(5).buyNum(4).build());

        // 初始化总数
        redisTemplate.opsForValue().set("000000_ticks_total", 20, 1000L, TimeUnit.SECONDS);
        for (BuyTickReq req : arrayList) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    /*-----------------------------模拟并发代码块start---------------------------*/

                    client.lock(new DemoMutex<BuyTickReq>("票总数") {
                        @Override
                        public BuyTickReq execute() {
                            UUID uuid = UUID.randomUUID();
                            // 获取票的总数
                            int total = (int) redisTemplate.opsForValue().get("000000_ticks_total");
                            System.out.println("票总数：" + total);
                            // 判断票数是否足够
                            if (req.getBuyNum() > total) {
                                throw new RuntimeException(uuid.toString() + "票数不足");
                            }
                            // 用户购购票过程
                            int remainder = total - req.getBuyNum();
                            redisTemplate.opsForValue().set("000000_ticks_total", remainder, 1000L, TimeUnit.SECONDS);
                            System.out.println(uuid.toString() + "用户：" + req.getUsrId() + "购买成功：" + req.getBuyNum());
                            // 购买成功剩余票数
                            System.out.println(uuid.toString() + "票剩余:" + remainder);
                            return null;
                        }
                    });
                    /*-----------------------------模拟并发代码块end---------------------------*/
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            countDownLatch.countDown();
        }

        System.in.read();
    }
}
@Data
@Builder
class BuyTickReq {
    private Integer usrId;
    private Integer buyNum;
}
