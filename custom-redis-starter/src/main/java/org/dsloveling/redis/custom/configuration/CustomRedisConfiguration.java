package org.dsloveling.redis.custom.configuration;

import org.dsloveling.redis.custom.constant.RedisModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Optional;

/**
 * 自动装配类
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 21:28
 */
@Configuration
@EnableAutoConfiguration(exclude = {RedisSingleConfig.class,RedisSentinelConfig.class,RedisClusterConfig.class})
public class CustomRedisConfiguration {

    @Value("${redis.model}")
    private String model;

    @Autowired(required = false)
    private RedisSingleConfig redisSingleConfig;

    @Autowired(required = false)
    private RedisSentinelConfig redisSentinelConfig;

    @Autowired(required = false)
    private RedisClusterConfig redisClusterConfig;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        return RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer)))
                .build();
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> shortUrlRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> shortUrlRedisTemplate = new RedisTemplate<>();
        shortUrlRedisTemplate.setConnectionFactory(redisConnectionFactory);
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        shortUrlRedisTemplate.setKeySerializer(new StringRedisSerializer());
        shortUrlRedisTemplate.setValueSerializer(valueSerializer);
        shortUrlRedisTemplate.afterPropertiesSet();
        return shortUrlRedisTemplate;
    }

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
