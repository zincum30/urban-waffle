package com.module.api.controller;

import com.module.api.dto.request.CheckCertificationNumberDto;
import com.module.api.dto.request.SendEmailDto;
import com.module.api.service.EmailVerifyService;
import com.module.api.service.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {

    private final SendEmailService sendEmailService;
    private final EmailVerifyService verifyService;

    @GetMapping("/send")
    public void sendCertificationNumber(@RequestParam("address") String mailAddress)
            throws MessagingException, NoSuchAlgorithmException {
        sendEmailService.sendEmailForCertification(mailAddress);

    }

    @GetMapping("/verify")
    public void verifyCertificationNumber(
            @RequestParam("number") CheckCertificationNumberDto checkCertificationNumberDto
            ) {
        verifyService.verifyEmail(checkCertificationNumberDto.getMail(), checkCertificationNumberDto.getCertificationNumber());
    }

}
