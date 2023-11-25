package com.gdsc.oauth2example.controller;

import com.gdsc.oauth2example.dto.PostResponseDto;
import com.gdsc.oauth2example.dto.PostRequestDto;
import com.gdsc.oauth2example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RequestMapping("/post")
@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/save")
    public ResponseEntity<String> savePost(Principal principal, @RequestBody PostRequestDto requestDto) {
        postService.savePost(Long.parseLong(principal.getName()), requestDto);
        return new ResponseEntity<>("글 작성 완료", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostResponseDto>> postList(){
        return new ResponseEntity<>(postService.getPostList(),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePost(@RequestBody PostResponseDto postDto){
        postService.updatePost(postDto);
        return new ResponseEntity<>("수정 완료",HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePost(@RequestBody PostResponseDto postDto){
        postService.deletePost(postDto);
        return new ResponseEntity<>("삭제 완료",HttpStatus.OK);
    }
}
