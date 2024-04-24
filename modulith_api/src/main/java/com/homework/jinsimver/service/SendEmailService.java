package com.homework.jinsimver.service;

import com.homework.jinsimver.certification.CertificationNumberGenerator;
import com.homework.jinsimver.certification.CertificationRedisTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
//@PropertySource("classpath:application.yml")
//@AllArgsConstructor
@RequiredArgsConstructor
public class SendEmailService {

    // TODO :이메일 주소 설정...

//    @Value("${spring.mail.username}")
//    private String ADMIN_EMAIL;

    private final JavaMailSender mailSender;
    private final CertificationRedisTemplate certificationRedisTemplate;
    private final CertificationNumberGenerator generator;



    public void sendEmailForCertification(String email) throws NoSuchAlgorithmException, MessagingException {

        String certificationNumber = generator.createCertificationNumber();
        String content = "인증번호 : " + certificationNumber.toString();
        certificationRedisTemplate.saveCertificationNumber(email,certificationNumber);
        sendEmail(email,content);
    }

    private void sendEmail(String email, String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(email);
        helper.setFrom("ggm29@naver.com");
        helper.setSubject("인증번호");
        helper.setText(content);
        mailSender.send(mimeMessage);
    }




}
