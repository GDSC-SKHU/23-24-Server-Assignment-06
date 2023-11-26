package com.example.oauth2example.auth.controller;

import com.example.oauth2example.auth.dto.PostRequest;
import com.example.oauth2example.auth.dto.PostResponse;
import com.example.oauth2example.auth.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    @PostMapping("/create")
    public ResponseEntity<String> createPost( Principal principal, @RequestBody PostRequest requestDTO) {
        postService.createPost(Long.parseLong(principal.getName()), requestDTO);
        return new ResponseEntity<>("글 작성", HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<List<PostResponse>> readPost(){
        return new ResponseEntity<>(postService.readPost(), HttpStatus.OK);
    }


}