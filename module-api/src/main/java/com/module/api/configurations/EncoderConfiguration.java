package com.module.api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class EncoderConfiguration {

    @Bean
    public BCryptPasswordEncoder encoder(){return new BCryptPasswordEncoder();}
}
