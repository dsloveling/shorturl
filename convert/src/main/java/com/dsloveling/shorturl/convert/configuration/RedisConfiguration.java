package com.dsloveling.shorturl.convert.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author dsloveling
 * @version 1.0
 * @date2020-04-03 15:00
 */
@Configuration
public class RedisConfiguration {

    @Bean(name = "redisTemplate")
    @Primary
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
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory)).build();
    }
}
