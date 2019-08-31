package i.am.whp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wuhepeng
 * @date 2019/8/9
 */
@Aspect
@Component
public class AspectOrientedProgramming {

    private Logger logger = LoggerFactory.getLogger(AspectOrientedProgramming.class);

    @Pointcut("execution(public * i.am.whp.controller.*.*(..))")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void beforeController(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RequestMapping annotation = signature.getMethod().getAnnotation(RequestMapping.class);
        logger.info("------{}------controller-----start---------", annotation.value());
    }

    @After("pointcut()")
    public void afterController(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RequestMapping annotation = signature.getMethod().getAnnotation(RequestMapping.class);
        logger.info("------{}------controller-----end---------", annotation.value());
    }
}
