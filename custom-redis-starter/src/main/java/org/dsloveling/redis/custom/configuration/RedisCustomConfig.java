package org.dsloveling.redis.custom.configuration;

import org.dsloveling.redis.custom.constant.RedisModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.Optional;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 23:43
 */
@Configuration
@EnableConfigurationProperties({RedisSingleConfig.class,RedisClusterConfig.class,RedisSentinelConfig.class})
public class RedisCustomConfig {
    @Value("${redis.model}")
    private String model;

    @Autowired(required = false)
    private RedisSingleConfig redisSingleConfig;

    @Autowired(required = false)
    private RedisSentinelConfig redisSentinelConfig;

    @Autowired(required = false)
    private RedisClusterConfig redisClusterConfig;

    @Bean
    @Primary
    public RedisConnectionFactory redisConnectionFactory() {
        if (redisSingleConfig != null && RedisModel.SINGLE.getModel().equals(model)) {
            return new LettuceConnectionFactory(getRedisSingleConfiguration(redisSingleConfig));
        }
        if (redisSentinelConfig != null && RedisModel.SENTINEL.getModel().equals(model)) {
            return new LettuceConnectionFactory(getRedisSentinelConfiguration(redisSentinelConfig));
        }
        if (redisClusterConfig != null && RedisModel.CLUSTER.getModel().equals(model)) {
            return new LettuceConnectionFactory(getRedisClusterConfiguration(redisClusterConfig));
        }
        throw new IllegalArgumentException("redis配置出错,单机、集群、哨兵均无配置");
    }

    private RedisConfiguration getRedisSingleConfiguration(RedisSingleConfig redisSingleConfiguration) {
        RedisConfiguration redisConfiguration = new RedisStandaloneConfiguration(redisSingleConfiguration.getHost(),
                redisSingleConfiguration.getPort());
        ((RedisStandaloneConfiguration) redisConfiguration).setDatabase(redisSingleConfiguration.getDatabase());
        Optional.ofNullable(redisSingleConfiguration.getPassword())
                .ifPresent(((RedisStandaloneConfiguration) redisConfiguration)::setPassword);
        return redisConfiguration;
    }

    private RedisConfiguration getRedisSentinelConfiguration(RedisSentinelConfig redisSentinelConfiguration) {
        RedisConfiguration redisConfiguration =
                new RedisSentinelConfiguration(redisSentinelConfiguration.getMaster()
                        , redisSentinelConfiguration.getNodes());
        Optional.ofNullable(redisSentinelConfiguration.getPassword())
                .ifPresent(((RedisStandaloneConfiguration) redisConfiguration)::setPassword);
        return redisConfiguration;
    }

    private RedisConfiguration getRedisClusterConfiguration(RedisClusterConfig redisClusterConfiguration) {
        RedisConfiguration redisConfiguration =
                new RedisClusterConfiguration(redisClusterConfiguration.getNodes());
        Optional.ofNullable(redisClusterConfiguration.getPassword())
                .ifPresent(((RedisClusterConfiguration) redisConfiguration)::setPassword);
        return redisConfiguration;
    }

}
