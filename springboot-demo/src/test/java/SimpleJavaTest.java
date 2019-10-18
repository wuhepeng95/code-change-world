import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SimpleJavaTest {
    @Test
    public void arrayToJson() {
        List<Bean> lists = new ArrayList<>();
        Bean bean = new Bean();
        bean.setS1("111");
        bean.setS2("2222");
        lists.add(bean);

        Bean bean1 = new Bean();
        bean1.setS1("111111111111");
        bean1.setS2("2222222222222");
        lists.add(bean1);
        System.out.println(JSONObject.toJSONString(lists));


    }
    class Bean{
        private String s1;
        private String s2;

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }
    }

    @Test
    public void name() {
        int threadCount = 100;

        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("新增加线程" + i);
            System.out.println("还剩余" + latch.getCount() + "未准备！");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        System.out.println("GOOGOGOGOOGOGOG");
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
}
