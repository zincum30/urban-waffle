package com.module.api.controller;

import com.module.api.annotation.JwtRequired;
import com.module.api.dto.request.comment.CreateCommentRequest;
import com.module.api.dto.request.comment.PostCommentRequest;
import com.module.api.dto.response.CommentsResponse;
import com.module.api.service.comment.CommentService;
import com.module.api.service.facade.CreateCommentFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("api/comments")
@Tag(name = "댓글 API")
public class CommentController {

    private final CommentService commentService;
    private final CreateCommentFacade createCommentFacade;


    @Operation(summary = "댓글 전체 목록 불러오기")
    @GetMapping("/{post-id}")
    public ResponseEntity<List<CommentsResponse>> fetchCommentsList(@PathVariable(name = "post-id") Long postId, Pageable pageable) {
        return ResponseEntity.ok(commentService.fetchCommentList(postId, pageable));
    }


    @Operation(summary = "댓글 수정")
    @PutMapping("/{comment-id}")
    public void updateComment(@PathVariable(name = "comment-id") Long commentId, Authentication authentication, String detail) {
        Long userId = (Long) authentication.getPrincipal();
        commentService.updateComment(commentId, userId, detail);
    }


    @Operation(summary = "댓글 생성")
    @PostMapping("/{post-id}")
    public void postComment(@PathVariable(name = "post-id") Long postId, @RequestBody PostCommentRequest commentRequest) {
        createCommentFacade.createComment(postId, commentRequest);
    }


    @Operation(summary = "대댓글 생성")
    @PostMapping("{comment-id}/reply")
    public void postReply(@PathVariable(name = "comment-id") Long commentId, Authentication authentication, @RequestBody CreateCommentRequest createCommentRequest) {
        Long userId = (Long) authentication.getPrincipal();
        commentService.addReply(commentId, userId, createCommentRequest);
    }

    @Operation(summary = "대댓글 전체 목록 불러오기")
    @GetMapping("{comment-id}/reply")
    public void fetchReplies(@PathVariable(name = "comment-id") Long commentId) {

    }

    @Operation(summary = "대댓글 수정")
    @PutMapping("{comment-id}/reply/{reply-id}")
    public void editReply(@PathVariable(name = "comment-id")Long commentId, @PathVariable(name = "reply-id")Long replyId) {

    }


    @Operation(summary = "대댓글 삭제")
    @DeleteMapping("{comment-id}/reply/{reply-id}")
    public void deleteReply(@PathVariable(name = "comment-id") Long commentId, @PathVariable(name = "reply-id") Long replyId) {

    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{comment-id}")
    public void deleteComment(@PathVariable(name = "comment-id") Long commentId, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        commentService.deleteComment(commentId, userId);

    }

}
