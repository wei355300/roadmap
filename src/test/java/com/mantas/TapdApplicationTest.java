package com.mantas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ActiveProfiles;

@EnableScheduling
@EnableCaching
@SpringBootApplication
@ActiveProfiles("test")
public class TapdApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(TapdApplicationTest.class, args);
    }

}
