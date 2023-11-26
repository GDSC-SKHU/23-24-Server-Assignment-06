package com.gdsc.oauth2example.controller;

import com.gdsc.oauth2example.domain.Post;
import com.gdsc.oauth2example.domain.User;
import com.gdsc.oauth2example.dto.PostRequest;
import com.gdsc.oauth2example.dto.PostResponse;
import com.gdsc.oauth2example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostRequest postRequest,
                                               @AuthenticationPrincipal User user) {
        Post post = postService.save(postRequest, user);
        return ResponseEntity.ok(PostResponse.createInstance(post));
    }

    @GetMapping
    public ResponseEntity<Page<PostResponse>> page(@PageableDefault Pageable pageable) {
        Page<PostResponse> posts = postService.findAll(pageable).map(PostResponse::createInstance);
        return ResponseEntity.ok(posts);
    }
}
