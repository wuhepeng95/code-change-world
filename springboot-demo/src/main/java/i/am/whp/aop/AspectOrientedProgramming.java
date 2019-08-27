package i.am.whp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
        logger.info("----------------before---------------------");
        System.out.println("Logging before " + joinPoint.getSignature().getName());
    }

    @After("pointcut()")
    public void afterController(JoinPoint joinPoint) {
        System.out.println("Logging before " + joinPoint.getSignature().getName());
        logger.info("----------------after---------------------");
    }
}
