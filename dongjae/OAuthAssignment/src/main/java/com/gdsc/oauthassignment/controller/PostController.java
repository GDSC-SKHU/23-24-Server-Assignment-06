package com.gdsc.oauthassignment.controller;

import com.gdsc.oauthassignment.common.dto.BaseResponse;
import com.gdsc.oauthassignment.config.resolver.UserId;
import com.gdsc.oauthassignment.controller.dto.request.post.PostRequestDto;
import com.gdsc.oauthassignment.controller.dto.response.post.GetAllPostResponseDto;
import com.gdsc.oauthassignment.exception.SuccessCode;
import com.gdsc.oauthassignment.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse addPost(@UserId Long userId, @RequestBody @Valid PostRequestDto postRequestDto){
        postService.addPost(postRequestDto, userId);
        return BaseResponse.success(SuccessCode.GUESTBOOK_CREATE_SUCCESS);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GetAllPostResponseDto> getAllGuestBookList(@UserId Long userId){
        final GetAllPostResponseDto data = postService.getAllPost(userId);
        return BaseResponse.success(SuccessCode.GET_ALL_GUESTBOOK_SUCCESS, data);
    }


}
