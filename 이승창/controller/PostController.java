package com.gdsc.noticeservice.controller;

import com.gdsc.noticeservice.domain.Post;
import com.gdsc.noticeservice.dto.PostDto;
import com.gdsc.noticeservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/new")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity<>("게시글 작성", HttpStatus.OK);
    }
    @GetMapping("/{title}")
    public PostDto callPost(@PathVariable("title")String title){
        Post post = postService.findPostByTitle(title);
        return post.makePostToDto();
    }
}
