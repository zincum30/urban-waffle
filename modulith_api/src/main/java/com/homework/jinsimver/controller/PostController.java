package com.homework.jinsimver.controller;

import com.homework.jinsimver.annotation.swagger.SaveDraft;
import com.homework.jinsimver.annotation.JwtRequired;
import com.homework.jinsimver.dto.request.CreatePostContentDto;
import com.homework.jinsimver.dto.response.FetchPostContentResponse;
import com.homework.jinsimver.dto.response.FetchPostListResponse;
import com.homework.jinsimver.service.post.PostContentService;
import com.homework.jinsimver.service.post.PostImageService;
import com.homework.jinsimver.service.post.PostMetaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@JwtRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "포스트 컨트롤러")
public class PostController {

    private final PostMetaService postMetaService;
    private final PostImageService postImageService;
    private final PostContentService postContentService;

    @SaveDraft
    @PostMapping("/draft")
    public ResponseEntity<Long> addTempPost(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        Long postId = postMetaService.createMetaPost(userId);
        return ResponseEntity.ok(postId);
    }

    @PostMapping("/draft/{post-id}")
    public void addTempPostImage(@RequestParam(value = "image") MultipartFile image, @PathVariable(name = "post-id") Long postId) {
        postImageService.addImage(postId, image);
    }

    @GetMapping
    public ResponseEntity<FetchPostListResponse> fetchPostList(PageRequest pageRequest) {
        FetchPostListResponse postList = postMetaService.fetchPostsList(pageRequest);
        return ResponseEntity.ok().body(postList);
    }

    @GetMapping("/{post-id}")
    public ResponseEntity<FetchPostContentResponse> fetchPostContent(@RequestParam(name = "post-id") Long postId) {
        FetchPostContentResponse postContent = postContentService.fetchPostContent(postId);
        return ResponseEntity.ok().body(postContent);
    }


    @PostMapping("/{post-id}")
    public ResponseEntity<HttpStatus> createPostContent(@PathVariable(name = "post-id") Long postId, @RequestBody CreatePostContentDto createPostContentDto) {

        postContentService.savePostContent(postId, createPostContentDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }


//    @PutMapping("/{post-id}")
//    public ResponseEntity<HttpStatus> modifyPostContent(@PathVariable(name = "post-id") Long postId, @RequestBody UpdatePostContentDto updatePostContentDto) {
//        postContentService.updatePostContent(updatePostContentDto);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }


    // TODO : 인자값으로 뭘 넘겨야 하나...
    @DeleteMapping("/{post-id}/img")
    public ResponseEntity<String> deleteImage(@PathVariable(name = "post-id")Long postId, String imageName) {
        postImageService.deleteImage(postId, imageName);
        return ResponseEntity.ok().body("DELETED");
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "post-id") Long postId, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        postMetaService.deletePost(postId, userId);
        return ResponseEntity.ok().body("DELETED");
    }

}
