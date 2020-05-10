package com.dsloveling.shorturl.convert.configuration;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 单日单ip调用次数限制
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 21:31
 */
@Configuration
@ConditionalOnProperty(prefix = "daily.invoke",name = "limit")
@Data
public class DailyInvokeLimit {
    private Integer limit;
}
