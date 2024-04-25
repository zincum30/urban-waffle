package com.module.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FetchPostContentResponse {

    String nickname;
    String content;
    LocalDateTime createdDate;
    LocalDateTime modifiedDte;

}
