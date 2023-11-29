package com.gdsc.googleloginexample.controller;


import com.gdsc.googleloginexample.common.dto.ApiResponse;
import com.gdsc.googleloginexample.dto.Request.PostCreateRequestDto;
import com.gdsc.googleloginexample.dto.Request.PostUpdateRequestDto;
import com.gdsc.googleloginexample.dto.Response.PostCreateResponseDto;
import com.gdsc.googleloginexample.dto.Response.PostDeleteResponseDto;
import com.gdsc.googleloginexample.dto.Response.PostResponseDto;
import com.gdsc.googleloginexample.exception.SuccessCode;
import com.gdsc.googleloginexample.jwt.TokenProvider;
import com.gdsc.googleloginexample.service.PostService;
import com.gdsc.googleloginexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/create") // @RequestHeader를 통해 token을 받아서 userId를 추출해서 userId와 post를 매핑해서 작성한다
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostCreateResponseDto> createPost(Principal principal,
                                                         @RequestBody @Validated final PostCreateRequestDto postCreateRequestDto) {
        final PostCreateResponseDto postCreateResponseDto = postService.createPost(principal, postCreateRequestDto);
        return ApiResponse.success(SuccessCode.CREATE_POST_SUCCESS, postCreateResponseDto);
    }

    @GetMapping("/check/{postId}") // postId로 게시글 조회
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PostResponseDto> getPost(@PathVariable final Long postId) {
        final PostResponseDto postResponseDto = postService.getPost(postId);
        return ApiResponse.success(SuccessCode.GET_POST_SUCCESS, postResponseDto);
    }

    @GetMapping("/all") // 사용자가 작성한 모든 post 조회
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<PostResponseDto>> getAllPostByUserId(Principal principal) {
        final List<PostResponseDto> postResponseDtoList = postService.getAllPostByUserId(principal);
        return ApiResponse.success(SuccessCode.GET_ALL_POST_SUCCESS, postResponseDtoList);
    }

    @PatchMapping("/update/{postId}") // 게시글 작성자가 게시글 id로 게시글 수정할 수 있도록
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PostResponseDto> updatePost(Principal principal, @PathVariable Long postId,
                                                   @RequestBody PostUpdateRequestDto postUpdateRequestDto) {

        final PostResponseDto postResponseDto = postService.updatePost(principal, postId, postUpdateRequestDto);

        return ApiResponse.success(SuccessCode.UPDATE_POST_SUCCESS, postResponseDto);
    }

    @DeleteMapping("delete/{postId}") // 게시글 작성자만 게시글을 삭제 할 수 있음
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PostResponseDto> deletePost(Principal principal, @PathVariable Long postId) {
        postService.deletePost(principal, postId);

        return ApiResponse.success(SuccessCode.DELETE_POST_SUCCESS);
    }
}
