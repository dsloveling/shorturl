package org.dsloveling.shorturl.convert.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 单日单ip调用次数限制
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 21:31
 */
@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "daily.invoke")
@Data
public class DailyInvokeLimit {
    private Integer limit;

    private Integer qps;
}
