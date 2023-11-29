package com.gdsc.oauthassignment.service;

import com.gdsc.oauthassignment.controller.dto.request.post.PostRequestDto;
import com.gdsc.oauthassignment.controller.dto.response.post.GetAllPostResponseDto;
import com.gdsc.oauthassignment.controller.dto.response.post.PostResponseDto;
import com.gdsc.oauthassignment.domain.post.Post;
import com.gdsc.oauthassignment.domain.user.User;
import com.gdsc.oauthassignment.exception.ErrorCode;
import com.gdsc.oauthassignment.exception.model.NotFoundException;
import com.gdsc.oauthassignment.repository.PostRepository;
import com.gdsc.oauthassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto addPost(PostRequestDto postRequestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .user(user)
                .ornamentId(postRequestDto.getOrnamentId())
                .build();
        postRepository.save(post);

        return PostResponseDto.of(post);
    }

    @Transactional
    public GetAllPostResponseDto getAllPost(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        List<Post> posts = postRepository.findAllByUser(user);
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : posts) {
            postResponseDtos.add(
                    PostResponseDto.of(post)
            );
        }
        return GetAllPostResponseDto.of(postResponseDtos);
    }

}
