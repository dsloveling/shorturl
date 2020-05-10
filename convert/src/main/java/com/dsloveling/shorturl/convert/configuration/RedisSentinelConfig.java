package com.dsloveling.shorturl.convert.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 21:33
 */
@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "redis.sentinel")
@Data
public class RedisSentinelConfig {
    private String master;

    private String password;

    private Set<String> nodes;

}
