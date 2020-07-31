package org.dsloveling.redis.custom.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 自动装配类
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 21:28
 */
@Configuration
@Import(RedisCustomConfig.class)
@EnableAutoConfiguration(exclude = CustomRedisConfiguration.class)
public class CustomRedisConfiguration {

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


}
