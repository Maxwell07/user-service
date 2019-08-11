package com.linqi.userservice.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Arrays;
import java.util.List;

@Configuration
public class RedisConfig {
    @Bean
    public ShardedJedisPool shardedJedisPool() {
        return new ShardedJedisPool(jedisPoolConfig(), jedisShardInfoList());
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean
    public List<JedisShardInfo> jedisShardInfoList() {
        return Arrays.asList(new JedisShardInfo("192.168.2.247", 6379, 3000));
    }
}
