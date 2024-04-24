package com.homework.jinsimver.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Getter
@AllArgsConstructor
public class FetchPostListResponse {

    private String nickname;
    private String thumnailPath;

}
