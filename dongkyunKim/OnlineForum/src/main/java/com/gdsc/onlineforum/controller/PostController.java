package com.gdsc.onlineforum.controller;


import com.gdsc.onlineforum.dto.request.PostCreateRequestDto;
import com.gdsc.onlineforum.dto.request.PostUpdateRequestDto;
import com.gdsc.onlineforum.dto.response.PostCreateResponseDto;
import com.gdsc.onlineforum.dto.response.PostDeleteResponseDto;
import com.gdsc.onlineforum.dto.response.PostResponseDto;
import com.gdsc.onlineforum.dto.response.PostUpdateResponseDto;
import com.gdsc.onlineforum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostCreateResponseDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, Principal principal) {
        return ResponseEntity.ok(postService.createPost(postCreateRequestDto, principal));
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<PostResponseDto>> getAllPost() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostUpdateResponseDto> updatePost(@PathVariable final Long postId, @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        return ResponseEntity.ok(postService.updatePost(postId, postUpdateRequestDto));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDeleteResponseDto> deletePost(@PathVariable final Long postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }
}
