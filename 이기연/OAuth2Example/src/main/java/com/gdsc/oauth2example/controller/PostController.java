package com.gdsc.oauth2example.controller;

import com.gdsc.oauth2example.dto.PostRequestDto;
import com.gdsc.oauth2example.dto.PostResponseDto;
import com.gdsc.oauth2example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/save")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, Principal principal) {
        // Principal을 이용하여 현재 로그인한 사용자의 정보를 가져올 수 있음
        // 여기에서는 간단하게 사용자의 ID만 가져왔다고 가정
        Long userId = Long.parseLong(principal.getName());

        // PostService를 사용하여 게시글 생성
        PostResponseDto createdPost = postService.createPost(postRequestDto, userId);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId) {
        // PostService를 사용하여 게시글 조회
        PostResponseDto post = postService.findPostById(postId);

        return ResponseEntity.ok(post);
    }
}
