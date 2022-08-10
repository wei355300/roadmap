package com.mantas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableScheduling
@EnableCaching
@SpringBootApplication
@EnableWebSecurity
public class MantasXApplication {

    public static void main(String[] args) {
        SpringApplication.run(MantasXApplication.class, args);
    }

}
