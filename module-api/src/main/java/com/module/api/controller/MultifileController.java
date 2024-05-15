package com.module.api.controller;

import com.module.api.exception.api.ApiErrorCode;
import com.module.api.exception.api.ApiException;
import com.module.api.exception.custom.CustomException;
import com.module.api.service.AwsS3Service;
import com.module.api.service.post.PostImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/files")
@Tag(name = "Multifile 컨트롤러")
public class MultifileController {

    private final PostImageService postImageService;
    private final AwsS3Service awsS3Service;

    @PostMapping("/images/tmp")
    public ResponseEntity<String> saveTemporaryImage(MultipartFile imageFile) {

        String url;

        try {
            url = awsS3Service.upload(imageFile);
        } catch (IOException e) {
            throw new ApiException(ApiErrorCode.UNEXPECTED_ERROR);
        }
        return ResponseEntity.ok(url);
    }
}
