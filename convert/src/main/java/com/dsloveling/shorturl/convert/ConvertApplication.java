package com.dsloveling.shorturl.convert;

import com.dsloveling.shorturl.convert.configuration.DailyInvokeLimit;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class ConvertApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConvertApplication.class, args);
    }

    @Bean
    public RateLimiter rateLimiter(DailyInvokeLimit dailyInvokeLimit) {
        RateLimiter rateLimiter = RateLimiter.create(dailyInvokeLimit.getQps());
        return rateLimiter;
    }

}
