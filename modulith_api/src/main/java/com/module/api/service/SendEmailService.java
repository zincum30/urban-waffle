package com.module.api.service;

import com.module.api.certification.CertificationNumberGenerator;
import com.module.api.certification.CertificationRedisTemplate;
import com.module.api.exception.CustomErrorCode;
import com.module.api.exception.CustomException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@PropertySource("classpath:application.yml")
@Service
@RequiredArgsConstructor
public class SendEmailService {

    private final JavaMailSender mailSender;
    private final CertificationRedisTemplate certificationRedisTemplate;
    private final CertificationNumberGenerator generator;

    private static final String SENDER_ADDRESS = "ggm29@naver.com";


    public void sendMail(String mail) {
        try {
            String content = generator.createCertificationNumber();
            certificationRedisTemplate.saveCertificationNumber(mail, content);
            createMessage(mail, content);
        } catch (MessagingException | NoSuchAlgorithmException e) {
            e.getStackTrace();
            throw new CustomException(CustomErrorCode.UNEXPECTED_ERROR);
        }
    }


    private void createMessage(String mail, String content) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(SENDER_ADDRESS);
            helper.setTo(mail);
            helper.setSubject("메일 제목");
            helper.setText(content);
            mailSender.send(message);

    }





}
