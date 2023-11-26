package com.gdsc.oauth2.controller;

import com.gdsc.oauth2.dto.PostRequest;
import com.gdsc.oauth2.dto.PostResponse;
import com.gdsc.oauth2.service.PostService;
import lombok.RequiredArgsConstructor;
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
        Long userId = Long.parseLong(principal.getName());
        PostResponse createdPost = postService.createPost(postRequest, userId);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        PostResponse post = postService.findPostById(postId);
        return ResponseEntity.ok(post);
    }
}
