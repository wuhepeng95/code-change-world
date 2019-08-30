//package i.am.whp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//
///**
// * @author wuhepeng
// * @date 2019/8/27
// */
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    public RedisConnectionFactory jedisConnectionFactory() {
//        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
//                .master("db0")
//                .sentinel("172.22.7.66", 6379);
//        sentinelConfig.setPassword("123456");
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(sentinelConfig);
//        return jedisConnectionFactory;
//    }
//
//}
