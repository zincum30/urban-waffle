package com.module.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication(scanBasePackages = "io.hypersistence.utils.spring.repository")
@ComponentScan(basePackages = "com.module.api")
@EntityScan(basePackages = "com.module.api")
//@EnableJpaRepositories(basePackages = {"com.module.api", "io.hypersistence.utils.spring.repository"})
//@EnableJpaAuditing
@EnableScheduling
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
