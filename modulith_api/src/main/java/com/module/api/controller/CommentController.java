package com.module.api.controller;

import com.module.api.annotation.JwtRequired;
import com.module.api.dto.request.CreateCommentDto;
import com.module.api.dto.request.PostCommentRequest;
import com.module.api.dto.response.FetchCommentListResponse;
import com.module.api.service.comment.CommentService;
import com.module.api.service.facade.CreateCommentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@JwtRequired
@RequiredArgsConstructor
// 코멘트 수정 / 삭제 / reply 등에는 post에 대한 정보가 필요 한가?
@RequestMapping("api/v1")
public class CommentController {

    private final CommentService commentService;
    private final CreateCommentFacade createCommentFacade;

    @GetMapping("/posts/{post-id}/comments")
    public ResponseEntity<List<FetchCommentListResponse>> fetchCommentsList(@PathVariable(name = "post-id") Long postId, Pageable pageable) {
        return ResponseEntity.ok(commentService.fetchCommentList(postId, pageable));

    }


    @PostMapping("/posts/{post-id}/comments")
    public void postComment(@PathVariable(name = "post-id") Long postId, @RequestBody PostCommentRequest commentRequest) {
        createCommentFacade.createComment(postId, commentRequest);
    }


    @PostMapping("/comments/reply/{comment-id}")
    public ResponseEntity<HttpStatus> postReply(@PathVariable(name = "comment-id") Long commentId, Authentication authentication, @RequestBody CreateCommentDto createCommentDto) {
        Long userId = (Long) authentication.getPrincipal();
        commentService.addReply(commentId, userId, createCommentDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/comments/{comment-id}")
    public void updateComment(@PathVariable(name = "comment-id") Long commentId, Authentication authentication, String detail) {
        Long userId = (Long) authentication.getPrincipal();
        commentService.updateComment(commentId, userId, detail);

    }

    @DeleteMapping("comments/{comment-id}")
    public void deleteComment(@PathVariable(name = "comment-id") Long commentId, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        commentService.deleteComment(commentId, userId);

    }

}
