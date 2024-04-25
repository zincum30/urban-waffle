package com.module.api.controller;

import com.module.api.dto.request.CheckCertificationNumberDto;
import com.module.api.dto.request.SendEmailDto;
import com.module.api.service.EmailVerifyService;
import com.module.api.service.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mails")
public class MailController {

    private final SendEmailService sendEmailService;
    private final EmailVerifyService verifyService;

    @PostMapping("/send-certification")
    public ResponseEntity<HttpStatus> sendCertificationNumber(@RequestBody SendEmailDto sendEmailDto)
            throws MessagingException, NoSuchAlgorithmException {
        sendEmailService.sendEmailForCertification(sendEmailDto.getEmail());
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping("/verify")
    public ResponseEntity<HttpStatus> verifyCertificationNumber(
            @RequestParam(name = "email") String email,
            @RequestBody CheckCertificationNumberDto checkCertificationNumberDto
            ) {
        verifyService.verifyEmail(email, checkCertificationNumberDto.getCertificationNumber());
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
