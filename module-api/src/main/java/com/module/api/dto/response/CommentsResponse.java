package com.module.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "댓글 목록 전체 조회")
public class CommentsResponse {


    private String nickname;
    private String detail;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<CommentsResponse> replies;

}
