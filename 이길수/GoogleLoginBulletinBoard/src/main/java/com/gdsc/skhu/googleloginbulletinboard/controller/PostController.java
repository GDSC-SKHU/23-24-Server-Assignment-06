package com.gdsc.skhu.googleloginbulletinboard.controller;

import com.gdsc.skhu.googleloginbulletinboard.dto.PostRequest;
import com.gdsc.skhu.googleloginbulletinboard.dto.PostResponse;
import com.gdsc.skhu.googleloginbulletinboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 생성
    @PostMapping("/new")
    public ResponseEntity<String> createPost(Principal principal, @RequestBody PostRequest requestDto) {
        postService.createPost(Long.parseLong(principal.getName()), requestDto);
        return new ResponseEntity<>("게시글 작성", HttpStatus.OK);
    }

    // 게시글 제목으로 정보 불러오기
    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> readPost() {
        return new ResponseEntity<>(postService.readPost(), HttpStatus.OK);
    }
}
