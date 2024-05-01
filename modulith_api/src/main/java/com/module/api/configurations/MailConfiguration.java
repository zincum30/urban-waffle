package com.module.api.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.hibernate.internal.util.collections.CollectionHelper.asProperties;

@Configuration
@ConditionalOnProperty(prefix = "spring.mail")
public class MailConfiguration {

    @Value("${username}")
    private String MAIL_USER_NAME;

    @Value("${password")
    private String MAIL_PASSWORD;

    @Value("${host}")
    private String MAIL_HOST;

    @Value("${port}")
    private int MAIL_PORT;


    @Bean
    @ConditionalOnMissingBean(JavaMailSender.class)
    JavaMailSenderImpl javaMailSender(MailProperties properties) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        applyProperties(properties, javaMailSender);
        return javaMailSender;
    }


    private void applyProperties(MailProperties properties, JavaMailSenderImpl javaMailSender) {

        javaMailSender.setHost(MAIL_HOST);
        javaMailSender.setPort(MAIL_PORT);
        javaMailSender.setUsername(MAIL_USER_NAME);
        javaMailSender.setPassword(MAIL_PASSWORD);
        javaMailSender.setJavaMailProperties(asProperties(properties.getProperties()));

    }

}
