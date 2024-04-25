package com.module.api.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class AddPostImageRequest {

    MultipartFile image;

}
