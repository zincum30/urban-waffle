package com.module.api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EncoderConfiguration {

    @Bean
    public BCryptPasswordEncoder encoder(){return new BCryptPasswordEncoder();}
}
