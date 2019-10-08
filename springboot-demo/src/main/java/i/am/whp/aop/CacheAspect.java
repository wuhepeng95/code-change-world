package i.am.whp.aop;

import i.am.whp.annotation.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author wuhepeng
 * @date 2019/8/27
 */
@Aspect
@Component
public class CacheAspect {

    private Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    @Resource(name = "initRedisTemplate")
    RedisTemplate redisTemplate;

    @Pointcut("@annotation(i.am.whp.annotation.Cache)")
    public void cachePointcut() {

    }

    @Around("cachePointcut()&&@annotation(cache)")
    public Object around(ProceedingJoinPoint joinPoint, Cache cache) throws Throwable {
        String keyName = cache.keyName();
        long expireTime = cache.expireTime();
        // 不关心入参 只有key决定
//        Object[] args = joinPoint.getArgs();
//        for (Object param : args) {
//            keyName += ("_" + param.toString());
//        }
        // 查看是否有缓存 有 直接去缓存返回
        if (redisTemplate.hasKey(keyName)) {
            Object result = redisTemplate.opsForValue().get(keyName);
            logger.info("调用方法：{},从Redis中读取", joinPoint.getSignature());
            return result;
        }
        Object result = joinPoint.proceed();
        // 如果没有缓存 将结果存入缓存
        // 不加TimeUnit.SECONDS 就变成了offset 前面补偿……
        redisTemplate.opsForValue().set(keyName, result, expireTime, TimeUnit.SECONDS);
        logger.info("调用方法：{},从DB中读取", joinPoint.getSignature());
        return result;
    }
}
