package com.gdsc.oauth2example.controller;

import com.gdsc.oauth2example.dto.PostRequestDto;
import com.gdsc.oauth2example.dto.PostResponseDto;
import com.gdsc.oauth2example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, Principal principal){
        Long userId = Long.parseLong(principal.getName());
        return ResponseEntity.ok(postService.createPost(postRequestDto, userId));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> post(@PathVariable Long postId){
        return ResponseEntity.ok(postService.findPostById(postId));
    }
}
