package com.dsloveling.shorturl.convert;

import com.dsloveling.shorturl.convert.configuration.DailyInvokeLimit;
import com.dsloveling.shorturl.convert.configuration.WhiteIpInterceptor;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class ConvertApplication {

    @Autowired
    private WhiteIpInterceptor whiteIpInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(ConvertApplication.class, args);
    }

    @Bean
    public RateLimiter rateLimiter(DailyInvokeLimit dailyInvokeLimit) {
        RateLimiter rateLimiter = RateLimiter.create(dailyInvokeLimit.getQps());
        return rateLimiter;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(whiteIpInterceptor).addPathPatterns("/**");
            }
        };
    }

}
