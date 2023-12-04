package com.gdsc.glogintest.controller;

import com.gdsc.glogintest.dto.PostRequestDto;
import com.gdsc.glogintest.dto.PostResponseDto;
import com.gdsc.glogintest.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public String save(@RequestBody PostRequestDto postRequestDto){
        return postService.savePost(postRequestDto);
    }

    @GetMapping("/{id}")
    public PostResponseDto findOne(@PathVariable Long id){
        return postService.findOne(id);
    }

}
