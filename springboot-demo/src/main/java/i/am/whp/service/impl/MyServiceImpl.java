package i.am.whp.service.impl;

import i.am.whp.annotation.Cache;
import i.am.whp.annotation.LogCost;
import i.am.whp.domain.GetDataParam;
import i.am.whp.mapper.local.MyTableMapper;
import i.am.whp.model.MyTable;
import i.am.whp.service.MyService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MyServiceImpl implements MyService<HashMap<String, String>> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MyTableMapper myTableMapper;

    @Override
    public HashMap<String, String> hi() {
        HashMap<String, String> model = new HashMap<>();
        model.put("code", "200");
        model.put("msg", "hello");
        return model;
    }

    @Override
    @LogCost
    @Cache(keyName = "testKey", expireTime = 20)
    public List<MyTable> getData(GetDataParam param) {
        new Thread(() -> System.out.println("开启线程1：" + MDC.get("guid"))).start();
        System.out.println("service:" + MDC.get("guid"));
//        手动继承
//        Map<String, String> m = MDC.getCopyOfContextMap();
//        MDC.setContextMap(m);
        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2" + System.identityHashCode(MDC.getCopyOfContextMap()));
            System.out.println("开启线程2：" + MDC.get("guid"));
        }).start();
        return myTableMapper.getList(param);
    }

    @Override
    public int getCount(GetDataParam param) {
        return myTableMapper.getCount(param);
    }
}
