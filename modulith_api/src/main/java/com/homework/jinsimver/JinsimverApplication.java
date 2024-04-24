package com.homework.jinsimver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan(basePackages = "com.homework.jinsimver")
@EnableJpaRepositories(basePackages = "io.hypersistence.utils.spring.repository")
@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication(scanBasePackages = "io.hypersistence.utils.spring.repository")
public class JinsimverApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinsimverApplication.class, args);
    }

}
