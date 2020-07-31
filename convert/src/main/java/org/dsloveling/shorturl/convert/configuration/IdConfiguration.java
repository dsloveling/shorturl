package org.dsloveling.shorturl.convert.configuration;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-04-03 15:02
 */
@Configuration
public class IdConfiguration {

    @Bean
    public Snowflake getId() {
        return IdUtil.createSnowflake(0L,0L);
    }
}
