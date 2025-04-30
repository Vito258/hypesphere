package com.elon.hypesphere.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MyRedissonConfig {
    private static final String REDISSON_CONFIG_FILE = "redisson-config.yaml";

    public MyRedissonConfig() {
        System.setProperty("redisson.config", REDISSON_CONFIG_FILE);
    }

    /**
     * RedissonClient
     * 所有的RedissonClient实例，都会共享同一个连接池
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.56.10:6379");
        return Redisson.create(config);
    }
}
