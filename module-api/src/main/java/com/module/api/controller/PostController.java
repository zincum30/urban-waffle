package com.module.api.controller;

import com.module.api.dto.request.CreatePostContentDto;
import com.module.api.dto.request.UpdatePostContentDto;
import com.module.api.dto.response.FetchPostContentResponse;
import com.module.api.dto.response.FetchPostListResponse;
import com.module.api.service.AwsS3Service;
import com.module.api.service.post.PostContentService;
import com.module.api.service.post.PostImageService;
import com.module.api.service.post.PostMetaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


//@JwtRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "포스트 컨트롤러")
public class PostController {

    private final PostMetaService postMetaService;
    private final PostImageService postImageService;
    private final PostContentService postContentService;
    private final AwsS3Service awsS3Service;


//    @PostMapping("/draft")
//    public ResponseEntity<Long> addTempPost(Authentication authentication) {
//        Long userId = (Long) authentication.getPrincipal();
//        Long postId = postMetaService.createMetaPost(userId);
//        return ResponseEntity.ok(postId);
//    }

    @PostMapping("/{post-id}/image")
    public void addTempPostImage(@RequestParam(value = "image") MultipartFile image) throws IOException {
        awsS3Service.upload(image);
    }

    @GetMapping
    public ResponseEntity<List<FetchPostListResponse>> fetchPostList() {
        return ResponseEntity.ok(postMetaService.fetchPostsList(null));
    }

    @GetMapping("/{post-id}")
    public ResponseEntity<FetchPostContentResponse> fetchPostContent(@RequestParam(name = "post-id") Long postId) {
        FetchPostContentResponse postContent = postContentService.fetchPostContent(postId);
        return ResponseEntity.ok().body(postContent);
    }

    // 포스트 작성 화면에서 일정 시간마다 호출해서 사용자가 작성중인 내용 임시 저장
    @PostMapping("/{post-id}/contents")
    public void createPostContent(@PathVariable(name = "post-id") Long postId, @RequestBody CreatePostContentDto createPostContentDto) {
        postContentService.savePostContent(postId, createPostContentDto);
    }


//    @PutMapping("/{post-id}/contents")
//    public void modifyPostContent(@PathVariable(name = "post-id") Long postId, @RequestBody UpdatePostContentDto updatePostContentDto) {
//        postContentService.updatePostContent(updatePostContentDto);
//    }


    // TODO : 인자값으로 뭘 넘겨야 하나...
    @DeleteMapping("/{post-id}/images")
    public void deleteImage(@PathVariable(name = "post-id") Long postId, @RequestParam("name") String imageName) {
        postImageService.deleteImage(postId, imageName);

    }

    @DeleteMapping("/{post-id}")
    public void deletePost(@PathVariable(name = "post-id") Long postId, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        postMetaService.deletePost(postId, userId);
    }



}