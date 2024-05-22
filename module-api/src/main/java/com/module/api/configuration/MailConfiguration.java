package com.module.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration(proxyBeanMethods = false)
@PropertySource("classpath:application.yml")
public class MailConfiguration {

    @Value("${spring.mail.username}")
    protected String MAIL_USER_NAME;

    @Value("${spring.mail.password}")
    private String MAIL_PASSWORD;

    @Value("${spring.mail.host}")
    private String MAIL_HOST;

    @Value("${spring.mail.port}")
    private int MAIL_PORT;



    @Bean
    private JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(MAIL_HOST);
        javaMailSender.setPort(MAIL_PORT);
        javaMailSender.setUsername(MAIL_USER_NAME);
        javaMailSender.setPassword(MAIL_PASSWORD);
        javaMailSender.setJavaMailProperties(getProperties());
        return javaMailSender;
    }


    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtps");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smto.ssl.enable", "true");
        return properties;
    }

}
