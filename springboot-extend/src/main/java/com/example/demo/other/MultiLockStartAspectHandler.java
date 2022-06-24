package com.example.demo.other;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Desc: 切面联锁Start处理
 * @Author: yande.wang@going-link.com
 * @Date: 2022-01-16 12:44
 */
@Aspect
@Component
public class MultiLockStartAspectHandler {

    @Around(value = "@annotation(multiLockStart)")
    public Object around(ProceedingJoinPoint joinPoint, MultiLockStart multiLockStart) throws Throwable {
        System.out.println("entrance multiLockStart aspect");
        return joinPoint.proceed();
    }

}
