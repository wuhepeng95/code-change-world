package i.am.whp.aop;

import i.am.whp.annotation.Cache;
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
public class CacheAspect {

    private Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    @Pointcut("@annotation(i.am.whp.annotation.Cache)")
    public void cachePointcut() {

    }

    @Around("cachePointcut()&&@annotation(cache)")
    public Object around(ProceedingJoinPoint joinPoint, Cache cache) throws Throwable {
        logger.info("开始缓存的方法 ");
        int expireTime = cache.expireTime();
        String keyName = cache.keyName();
        Object proceed = joinPoint.proceed();
        logger.info("缓存执行结束");
        return proceed;
    }
}
