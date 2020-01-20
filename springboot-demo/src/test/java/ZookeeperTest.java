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
 * ZK 加锁思想：
 *  根据路径生成zookeeper的node作为一个标识，只有这个节点
 *
 * @author wuhepeng
 * @date 2019/10/16
 */
public class ZookeeperTest extends SpringBootTestBase {

    @Value("${zookeeper.connect.string}")
    String zookeeperConnectString;

    @Resource
    private Client client;

    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void connectToZk() {
        ZookeeperLock testLock = new ZookeeperLock(zookeeperConnectString, "testLock");
        testLock.lock();
        testLock.unlock();
    }

    @Test
    public void testLock() throws IOException {
        // 模拟高并发 用户抢票
        CountDownLatch countDownLatch = new CountDownLatch(10);

        ArrayList<BuyTickReq> arrayList = new ArrayList();
        // 如果同个用户发了多个相同的请求 => 前端控制 路由过滤 后端控制
        // 防止超卖的情况
        //
        arrayList.add(BuyTickReq.builder().usrId(1).buyNum(1).build());// case1
        arrayList.add(BuyTickReq.builder().usrId(2).buyNum(2).build());// case2
        arrayList.add(BuyTickReq.builder().usrId(3).buyNum(3).build());// case3
        arrayList.add(BuyTickReq.builder().usrId(4).buyNum(4).build());// case4
        arrayList.add(BuyTickReq.builder().usrId(5).buyNum(5).build());// case5
        arrayList.add(BuyTickReq.builder().usrId(6).buyNum(6).build());// case6
        arrayList.add(BuyTickReq.builder().usrId(7).buyNum(7).build());// case7
        arrayList.add(BuyTickReq.builder().usrId(8).buyNum(8).build());// case8
        arrayList.add(BuyTickReq.builder().usrId(9).buyNum(9).build());// case9
        arrayList.add(BuyTickReq.builder().usrId(10).buyNum(10).build());// case10

        // 初始化总数
        redisTemplate.opsForValue().set("000000_ticks_total", 20, 1000L, TimeUnit.SECONDS);
        for (BuyTickReq req : arrayList) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    /*----------------模拟并发控制代码块start（相当于service业务代码）---------------*/
                    client.lock(new DemoMutex<BuyTickReq>(-1) {
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
