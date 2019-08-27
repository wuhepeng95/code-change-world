package i.am.whp.aop;

import i.am.whp.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wuhepeng
 * @date 2019/8/27
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(i.am.whp.annotation.Log)")
    public void logPointcut() {

    }

    @Around("logPointcut()&&@annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long cost = endTime - startTime;
        logger.info("methodName:{},executeTime:{},cost:{}", joinPoint.getSignature(), endTime, cost);
        return result;
    }
}
