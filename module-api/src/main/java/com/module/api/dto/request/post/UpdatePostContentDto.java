package com.module.api.dto.request.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdatePostContentDto {

    private Long postId;
    private String content;


}