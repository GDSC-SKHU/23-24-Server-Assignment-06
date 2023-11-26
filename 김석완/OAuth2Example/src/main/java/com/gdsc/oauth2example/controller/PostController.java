package com.gdsc.oauth2example.controller;

import com.gdsc.oauth2example.dto.PostRequestDto;
import com.gdsc.oauth2example.dto.PostResponseDto;
import com.gdsc.oauth2example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto postRequestDto, Principal principal){
        postService.createPost(postRequestDto, Long.parseLong(principal.getName()));
        return new ResponseEntity<>("작성 완료", HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> post(@PathVariable Long postId){
        return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.OK);
    }
}
