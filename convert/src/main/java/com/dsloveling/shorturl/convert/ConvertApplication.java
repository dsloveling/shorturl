package com.dsloveling.shorturl.convert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class ConvertApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConvertApplication.class, args);
    }

}
