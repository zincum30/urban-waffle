package com.module.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FetchCommentListResponse {

    private String nickname;
    private String detail;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<FetchCommentListResponse> replies;

}
