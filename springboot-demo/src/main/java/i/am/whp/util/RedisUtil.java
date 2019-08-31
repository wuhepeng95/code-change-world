package i.am.whp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author wuhepeng
 * @date 2019/8/30
 */
public class RedisUtil {
    @Autowired
    RedisTemplate redisTemplate;

    public Boolean hasKey(Object key) {
        return redisTemplate.hasKey(key);
    }

    public void set(String key,Object value){
         redisTemplate.opsForValue().set(key,value);
    }

    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

}
