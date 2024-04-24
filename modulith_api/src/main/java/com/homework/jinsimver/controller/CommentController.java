package com.homework.jinsimver.controller;

import com.homework.jinsimver.annotation.JwtRequired;
import com.homework.jinsimver.dto.request.CreateCommentDto;
import com.homework.jinsimver.dto.request.CreatePostContentDto;
import com.homework.jinsimver.dto.request.PostCommentRequest;
import com.homework.jinsimver.service.comment.CommentService;
import com.homework.jinsimver.service.facade.CreateCommentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.MediaSize;

@RestController
@JwtRequired
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CommentController {

    private final CommentService commentService;
    private final CreateCommentFacade createCommentFacade;

//    @GetMapping("/{postId}")
//    public ResponseEntity<Pagination<FetchCommentListResponse>> fetchCommentsList(@PathVariable(name = "postId")Long postId) {
//        return ResponseEntity.ok().body(commentService.selectCommentList(postId));
//
//    }
//

    @PostMapping("/posts/{post-id}/comment")
    public ResponseEntity<HttpStatus> postComment(@PathVariable(name = "post-id") Long postId, @RequestBody PostCommentRequest commentRequest) {
        createCommentFacade.createComment(postId, commentRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    // TODO : 근데 프론트에서 코멘트 아이디를 알 수가,,,있나..?
    @PostMapping("posts/{post-id}/comments/{comment-id}")
    public ResponseEntity<HttpStatus> postReply(@PathVariable(name ="post-id") Long postId, @PathVariable(name = "comment-id") Long commentId, Authentication authentication, @RequestBody CreateCommentDto createCommentDto) {
        Long userId = (Long) authentication.getPrincipal();
        commentService.addReply(postId, commentId, userId, createCommentDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("comments/{comment-id}")
    public ResponseEntity<HttpStatus> updateComment(@PathVariable(name = "comment-id") Long commentId, Authentication authentication, String detail) {
        Long userId = (Long) authentication.getPrincipal();
        commentService.updateComment(commentId, userId, detail);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{comment-id}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "comment-id") Long commentId, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.ok().body("DELETED");
    }

}
