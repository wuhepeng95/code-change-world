import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @author wuhepeng
 * @date 2020/1/20
 */
public class RedisTest extends SpringBootTestBase {

    // 使用StringRedisSerializer的序列化格式
    @Resource(name = "initRedisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest() {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        System.out.println(jedis.smembers("myset"));

        redisTemplate.opsForSet().add("myset", "a", "b", "c");
        System.out.println(redisTemplate.opsForSet().members("myset"));
    }
}
