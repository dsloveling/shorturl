package com.dsloveling.shorturl.convert.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 21:33
 */
@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "redis.cluster")
@Data
public class RedisClusterConfig {


    private String password;

    private List<String> nodes;

}
