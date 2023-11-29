package com.example.oauth.controller;

import com.example.oauth.dto.PostInfoDto;
import com.example.oauth.dto.PostSaveDto;
import com.example.oauth.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/create")
    public ResponseEntity<PostInfoDto> savePost(@RequestBody PostSaveDto postSaveDto, Principal principal) {
        return ResponseEntity.ok(postService.savePost(postSaveDto, principal));
    }

    @GetMapping("/search/{postId}")
    public ResponseEntity<PostInfoDto> searchPost(@PathVariable Long postId){
        return ResponseEntity.ok(postService.searchPost(postId));
    }
}
