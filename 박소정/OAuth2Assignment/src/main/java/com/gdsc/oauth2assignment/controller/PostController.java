package com.gdsc.oauth2assignment.controller;

import com.gdsc.oauth2assignment.dto.PostResDto;
import com.gdsc.oauth2assignment.dto.PostSaveReqDto;
import com.gdsc.oauth2assignment.service.PostService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {

    private final PostService postService;

    @PostMapping("/new")
    public String createPost(@RequestBody PostSaveReqDto reqDto, Principal principal) {
        return postService.createPost(reqDto, principal);
    }

    @GetMapping("/{id}")
    public PostResDto findPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }
}
