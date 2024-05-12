package com.module.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FetchPostListResponse {

    private String nickname;
    private String thumnailPath;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
