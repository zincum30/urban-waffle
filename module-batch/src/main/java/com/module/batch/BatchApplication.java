package com.module.batch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

// @ComponentScan(basePackages = "com.module.batch")
@EnableScheduling
// @EnableJpaRepositories(basePackages = "io.hypersistence.utils.spring.repository")
@SpringBootApplication
		// (scanBasePackages = "io.hypersistence.utils.spring.repository")
public class BatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

}
