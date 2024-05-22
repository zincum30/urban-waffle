package com.module.api.service;

import com.module.api.certification.CertificationNumberGenerator;
import com.module.api.certification.CertificationRedisTemplate;
import com.module.api.exception.api.ApiErrorCode;
import com.module.api.exception.api.ApiException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@PropertySource("classpath:application.yml")
@Service
@RequiredArgsConstructor
public class SendEmailService {


    private final JavaMailSender javaMailSender;
    private final CertificationRedisTemplate certificationRedisTemplate;
    private final CertificationNumberGenerator generator;

    private static final String SENDER_ADDRESS = "ggm29@naver.com";


    public void sendMail(String mail) {
        try {
            String content = generator.createCertificationNumber();
            certificationRedisTemplate.saveCertificationNumber(mail, content);
            createMessage(mail, content);
        } catch (MessagingException | NoSuchAlgorithmException e) {
            throw new ApiException(ApiErrorCode.UNEXPECTED_ERROR);
        }
    }


    private void createMessage(String mail, String content) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(SENDER_ADDRESS);
            helper.setTo(mail);
            helper.setSubject("메일 제목");
            helper.setText(content);
            javaMailSender.send(message);

    }





}
