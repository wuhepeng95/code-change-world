package i.am.whp.aop;

import i.am.whp.annotation.LogCost;
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
public class LogCostAspect {

    private Logger logger = LoggerFactory.getLogger(LogCostAspect.class);

    @Pointcut("@annotation(i.am.whp.annotation.LogCost)")
    public void logPointcut() {

    }

    @Around("logPointcut()&&@annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, LogCost log) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long cost = endTime - startTime;
        logger.info("调用方法methodName:{},耗时cost:{}", joinPoint.getSignature(), cost);
        return result;
    }
}
