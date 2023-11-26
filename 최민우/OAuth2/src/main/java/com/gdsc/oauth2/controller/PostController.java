package com.gdsc.oauth2.controller;

import com.gdsc.oauth2.dto.PostRequest;
import com.gdsc.oauth2.dto.PostResponse;
import com.gdsc.oauth2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/save")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest, Principal principal) {
        // Principal을 이용하여 현재 로그인한 사용자의 정보를 가져올 수 있음
        Long userId = Long.parseLong(principal.getName());

        // PostService를 사용하여 게시글 생성
        PostResponse createdPost = postService.createPost(postRequest, userId);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        // PostService를 사용하여 게시글 조회
        PostResponse post = postService.findPostById(postId);

        return ResponseEntity.ok(post);
    }
}
