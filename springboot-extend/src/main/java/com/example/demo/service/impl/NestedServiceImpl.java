package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.other.ApplicationContextProvider;
import com.example.demo.service.NestedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * todow 添加类描述
 *
 * @author hepeng.wu@going-link.com 2022/3/27 下午1:00
 */
@Service
public class NestedServiceImpl implements NestedService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertUser() {
//        User user = new User();
//        user.setName("AAA");
//        userMapper.insert(user);
        User user = userMapper.selectById(1508647586749054978L);
        user.setName("CCC");
        userMapper.updateById(user);

        try {
            applicationContextProvider.getBean(NestedService.class).updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        user.setName("DDD");
//        userMapper.updateById(user);

//        System.out.println(1/0);
    }

    // REQUIRES_NEW时候会死锁
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void updateUser(User user) {
//        user.setName("BBB");
//        userMapper.updateById(user);

        User user2 = userMapper.selectById(1508647586749054978L);
        user2.setName("CCC");
        userMapper.updateById(user2);

        try {
//            User userB = new User();
//            userB.setName("BBB");
//            userMapper.insert(userB);

//            User selectUser = userMapper.selectById(user.getId());
//            selectUser.setName("BBB");
//            userMapper.updateById(selectUser);
//            int i = 1 / 0;
//            selectUser.setName("CCC");
//            userMapper.updateById(selectUser);
        } catch (Exception e) {
//            e.printStackTrace();
//            User selectUser = userMapper.selectById(user.getId());
//            selectUser.setName("DDD");
//            userMapper.updateById(selectUser);
        }
    }

    // 1、谁抛出了异常（谁没有被try-catch） 谁要回滚
    // 2、子REQUIRED要回滚，父事务UnexpectedRollbackException
    // 3、子NEW/NESTED要回滚，父事务管不了
}
