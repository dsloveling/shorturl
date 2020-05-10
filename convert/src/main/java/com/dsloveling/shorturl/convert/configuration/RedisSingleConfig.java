package com.dsloveling.shorturl.convert.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dsloveling
 * @version 1.0
 * @date2020-04-03 15:00
 */
@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "redis.single")
@Data
public class RedisSingleConfig {

    private String host;

    private Integer database;

    private Long timeout;

    private Integer port;

    private String password;

}
