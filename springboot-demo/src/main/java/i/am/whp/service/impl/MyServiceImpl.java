package i.am.whp.service.impl;

import i.am.whp.annotation.Cache;
import i.am.whp.annotation.LogCost;
import i.am.whp.domain.GetDataParam;
import i.am.whp.mapper.local.MyTableMapper;
import i.am.whp.model.MyTable;
import i.am.whp.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

//expected single matching bean but found 2: myServiceImpl,myServiceImpl222
@Service
@Slf4j
public class MyServiceImpl implements MyService<HashMap<String, String>> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MyTableMapper myTableMapper;
    @Autowired
    MyService self;

    @Override
    public HashMap<String, String> hi() {
        HashMap<String, String> model = new HashMap<>();
        model.put("code", "200");
        model.put("msg", "hello");
        return model;
    }

    @Override
    @LogCost
    @Retryable(value = Exception.class, maxAttempts = 4, backoff = @Backoff(delay = 2000L, multiplier = 1))
    @Cache(keyName = "whp-test:get_data", expireTime = 20)
    public List<MyTable> getData(GetDataParam param) {
        new Thread(() -> System.out.println("开启线程1：" + MDC.get("guid"))).start();
        System.out.println("service:" + MDC.get("guid"));
//        手动继承
//        Map<String, String> m = MDC.getCopyOfContextMap();
//        MDC.setContextMap(m);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2" + System.identityHashCode(MDC.getCopyOfContextMap()));
            System.out.println("开启线程2：" + MDC.get("guid"));
        }).start();
        // 重试完才返回
        //int i = 1/ 0;
        return myTableMapper.getList(param);
    }

    @Override
    public int getCount(GetDataParam param) {
        return myTableMapper.getCount(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAndInsert(MyTable param) {
        myTableMapper.updateStatus(param.getId());
        myTableMapper.insert(param);
        return 0;
    }

    @Override
    public List<MyTable> whereTest(MyTable param) {
        return myTableMapper.whereTest(param);
    }

    @Override
    @Transactional
    public boolean testRollback() {
        myTableMapper.updateStatus(1);
        try {
            self.sqlException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            log.error("testRollback with error", e);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean sqlException() {
        int i = 1 / 0;
//        throw new UnexpectedRollbackException(
//                "1111Transaction rolled back because it has been marked as rollback-only1111");
//        MyTable mapperById = myTableMapper.getById(2);
//        throw new RuntimeException("error");
//        mapperById.setName(null);
//        myTableMapper.insert(mapperById);
        return false;
    }
}
