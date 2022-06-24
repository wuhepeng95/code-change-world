package com.example.demo.other;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Desc: 联锁开启注解，不会实际加锁，会在当前方法结束后释放当前线程加的联锁
 * @Author: yande.wang@going-link.com
 * @Date: 2022-01-16 12:37
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MultiLockStart {

}
