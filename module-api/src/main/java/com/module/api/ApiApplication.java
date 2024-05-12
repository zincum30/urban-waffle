package com.module.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@ComponentScan(basePackages = "com.module.api")
//@EnableJpaRepositories(basePackages = "io.hypersistence.utils.spring.repository")

@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "com.module.api")
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.module.api", "com.module.chat"})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
