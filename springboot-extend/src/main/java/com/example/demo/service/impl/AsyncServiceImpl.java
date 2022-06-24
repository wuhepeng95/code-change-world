package com.example.demo.service.impl;

import com.example.demo.domain.UserVersion;
import com.example.demo.mapper.UserVersionMapper;
import com.example.demo.other.ApplicationContextProvider;
import com.example.demo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * todow 添加类描述
 *
 * @author hepeng.wu@going-link.com 2022/4/2 上午11:35
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private UserVersionMapper userVersionMapper;

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testAsync() {
//        UserVersion userVersion = new UserVersion();
//        userVersion.setName("TEST1");
//        userVersion.setAge(12);
//        userVersionMapper.insert(userVersion);

//        userVersion.setAge(13);
//        userVersionMapper.update(userVersion, Wrapper.asWrapperType(UserVersion.class).);
//        applicationContextProvider.getBean(AsyncService.class).asyncMethod(userVersion);

        UserVersion userVersion = userVersionMapper.selectById(1);
        System.out.println("before" + userVersion);

        UserVersion userVersionNew = applicationContextProvider.getBean(AsyncService.class).updateNew();
        System.out.println("update1" + userVersionNew);

        UserVersion userVersion2 = userVersionMapper.selectById(1);
        System.out.println("after1" + userVersion2);

        UserVersion userVersion4 = applicationContextProvider.getBean(AsyncService.class).selectNew();
        System.out.println("select2" + userVersion4);


        UserVersion userVersion3 = userVersionMapper.queryById(1L);
        System.out.println("after2" + userVersion3);



//        userVersion2.seInterceptorChaintAge(222222);
//        userVersionMapper.updateById(userVersion2);
//        System.out.println("after update2" + userVersion2);


//        System.out.println(userVersionMapper.selectById(userVersionNew.getId()));
//        System.out.println(1/0);
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public UserVersion selectNew() {
        return userVersionMapper.selectById(1);
    }

    @Override
    @Async
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void asyncMethod(UserVersion userVersion) {

        userVersion.setId(null);
        userVersion.setName("TEST2");
        userVersion.setAge(12);
        userVersionMapper.insert(userVersion);

        System.out.println(userVersionMapper.selectById(userVersion.getId()));
        System.out.println(1 / 0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public UserVersion updateNew() {
//        UserVersion userVersion = new UserVersion();
//        userVersion.setName("TEST12");
//        userVersion.setAge(12);
//        userVersionMapper.insert(userVersion);

        UserVersion userVersion = userVersionMapper.selectById(1);
        userVersion.setAge(111111111);
        userVersionMapper.updateById(userVersion);
        return userVersion;
    }



}
