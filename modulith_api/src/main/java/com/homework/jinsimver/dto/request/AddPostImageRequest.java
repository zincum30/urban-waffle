package com.homework.jinsimver.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class AddPostImageRequest {

    MultipartFile image;

}
