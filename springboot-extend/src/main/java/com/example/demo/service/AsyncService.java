package com.example.demo.service;

import com.example.demo.domain.UserVersion;

/**
 * todow 添加类描述
 *
 * @author hepeng.wu@going-link.com 2022/4/2 上午11:35
 */
public interface AsyncService {

    void testAsync();

    void asyncMethod(UserVersion userVersion);

    UserVersion updateNew();

    UserVersion selectNew();
}
