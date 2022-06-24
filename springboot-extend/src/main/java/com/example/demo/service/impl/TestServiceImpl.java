package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.other.ApplicationContextProvider;
import com.example.demo.other.MultiLockStart;
import com.example.demo.service.TestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;
import java.util.Random;

@Service
@Log4j2
public class TestServiceImpl implements TestService {

    private static ThreadLocal<Integer> retryTimes = new ThreadLocal<>();

    @Autowired
    private ApplicationContextProvider applicationContextProvider;
    @Autowired
    private UserMapper userMapper;


    public void rollBackMethod(String arg) {

        retryTimes.set(retryTimes.get() == null ? 1 : retryTimes.get());

        try {
            //
            int i = 1 / 0;
        } catch (Exception e) {
            if (retryTimes.get() < 3) {

                System.out.println(Thread.currentThread().getName() + " retry time , e 【" + e.getMessage() + "】，times" + retryTimes.get());
                retryTimes.set(retryTimes.get() + 1);
                applicationContextProvider.getBean(TestServiceImpl.class).rollBackMethod(arg);
            } else {
                throw e;
            }
        } finally {
            retryTimes.set(null);
        }
    }

    //@Transactional(rollbackFor = Exception.class)
    @MultiLockStart // 可以切的到
    public void retryTest() {
        for (int i = 1; i <= 3; i++) {
            try {
                System.out.println("尝试第【" + i + "】次处理");
                applicationContextProvider.getBean(TestServiceImpl.class).doTest();
                break;
            } catch (Exception e) {
                // 不抛出异常了 saga暂时没有感应
                System.out.println("第【" + i + "】处理次败， 异常： " + e.getMessage());
                // 休眠3s后重试
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException interruptedException) {
//                    interruptedException.printStackTrace();
//                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void doTest() {

        String txName = TransactionSynchronizationManager.getCurrentTransactionName();
        Map<Object, Object> resourceMap = TransactionSynchronizationManager.getResourceMap();
        log.info("resourceMap ===== {}", resourceMap);
        User x = userMapper.selectById(1);
        x.setEmail("执行成功11");
        userMapper.updateById(x);
        if (new Random().nextInt(10) % 2 == 0) {
            throw new RuntimeException("业务处理出错了");
        }
//        int i = 1 / 0;
        User x2 = userMapper.selectById(2);
        x2.setEmail("执行成功21");
        userMapper.updateById(x2);
        // 业务处理
        System.out.println("业务处理成功");
    }

}
