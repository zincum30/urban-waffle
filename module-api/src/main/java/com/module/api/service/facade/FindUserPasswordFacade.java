package com.module.api.service.facade;

import com.module.api.dto.request.user.FindUserPasswordRequest;
import com.module.api.exception.api.ApiErrorCode;
import com.module.api.exception.api.ApiException;
import com.module.api.service.SendEmailService;
import com.module.api.service.user.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserPasswordFacade {

    private final UserDetailService userDetailService;
    private final SendEmailService sendEmailService;

    public void FindUserPassword(FindUserPasswordRequest findUserPasswordRequest) {
        String name = findUserPasswordRequest.getName();
        String email = findUserPasswordRequest.getEmail();
        if (userDetailService.matchUserDetail(name, email)) {
            sendEmailService.sendMail(email);
        } else {
            throw new ApiException(ApiErrorCode.USER_NOT_FOUND);
        }




    }

}
