package com.dsloveling.shorturl.convert.configuration;

import com.dsloveling.shorturl.convert.constant.RedisModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 23:43
 */
@Configuration
public class RedisCustomConfig {

    @Value("${redis.model}")
    private String model;

    @Bean
    @Primary
    public RedisConnectionFactory redisConnectionFactory(ApplicationContext applicationContext) {
        RedisSingleConfig redisSingleConfig = applicationContext.containsBeanDefinition(
                "redisSingleConfig") ? (RedisSingleConfig) applicationContext.getBean(
                "redisSingleConfig") : null;
        RedisSentinelConfig redisSentinelConfig= applicationContext.containsBeanDefinition(
                "redisSentinelConfig") ? (RedisSentinelConfig) applicationContext.getBean(
                "redisSentinelConfig") : null;
        RedisClusterConfig redisClusterConfig = applicationContext.containsBeanDefinition(
                "redisClusterConfig") ? (RedisClusterConfig) applicationContext.getBean(
                "redisClusterConfig") : null;
        if (redisSingleConfig != null && RedisModel.SINGLE.getModel().equals(model)) {
            return new LettuceConnectionFactory(getRedisSingleConfiguration(redisSingleConfig));
        }
        if (redisSentinelConfig != null  && RedisModel.SENTINEL.getModel().equals(model)) {
            return new LettuceConnectionFactory(getRedisSentinelConfiguration(redisSentinelConfig));
        }
        if (redisClusterConfig != null  && RedisModel.CLUSTER.getModel().equals(model)) {
            return new LettuceConnectionFactory(getRedisClusterConfiguration(redisClusterConfig));
        }
        throw new IllegalArgumentException("redis配置出错,单机、集群、哨兵均无配置");
    }

    private RedisConfiguration getRedisSingleConfiguration(RedisSingleConfig redisSingleConfiguration) {
        RedisConfiguration redisConfiguration = new RedisStandaloneConfiguration(redisSingleConfiguration.getHost(),
                redisSingleConfiguration.getPort());
        ((RedisStandaloneConfiguration) redisConfiguration).setDatabase(redisSingleConfiguration.getDatabase());
        ((RedisStandaloneConfiguration) redisConfiguration).setPassword(redisSingleConfiguration.getPassword());
        return redisConfiguration;
    }

    private RedisConfiguration getRedisSentinelConfiguration(RedisSentinelConfig redisSentinelConfiguration) {
        RedisConfiguration redisConfiguration =
                new org.springframework.data.redis.connection.RedisSentinelConfiguration(redisSentinelConfiguration.getMaster()
                        , redisSentinelConfiguration.getNodes());
        ((org.springframework.data.redis.connection.RedisSentinelConfiguration) redisConfiguration).setPassword(redisSentinelConfiguration.getPassword());
        return redisConfiguration;
    }

    private RedisConfiguration getRedisClusterConfiguration(RedisClusterConfig redisClusterConfiguration) {
        RedisConfiguration redisConfiguration =
                new org.springframework.data.redis.connection.RedisClusterConfiguration(redisClusterConfiguration.getNodes());
        ((org.springframework.data.redis.connection.RedisClusterConfiguration) redisConfiguration).setPassword(redisClusterConfiguration.getPassword());
        return redisConfiguration;
    }
}
