package com.module.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;


@Async
@SpringBootApplication(scanBasePackages = "io.hypersistence.utils.spring.repository")
@ComponentScan(basePackages = "com.module.api")
@EntityScan(basePackages = "com.module.api")
@EnableJpaRepositories(basePackages = {"com.module.api", "io.hypersistence.utils.spring.repository"})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
