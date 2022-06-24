package com.example.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * redisson配置
 */
@Configuration
public class MyRedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        //1、创建配置
        Config config = new Config();
        //Redis url should start with redis:// or rediss:// (for SSL connection)
        //可以用"rediss://"来启用SSL连接
        config.useSingleServer()
                .setAddress("redis://localhost:6379")
                .setPassword("123456")
                .setDatabase(0);

        //2、根据Config创建出RedissonClient实例
        return Redisson.create(config);
    }
}
