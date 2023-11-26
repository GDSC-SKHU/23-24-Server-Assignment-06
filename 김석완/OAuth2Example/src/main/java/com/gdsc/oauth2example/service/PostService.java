package com.gdsc.oauth2example.service;

import com.gdsc.oauth2example.domain.Post;
import com.gdsc.oauth2example.domain.User;
import com.gdsc.oauth2example.dto.PostRequestDto;
import com.gdsc.oauth2example.dto.PostResponseDto;
import com.gdsc.oauth2example.repository.PostRepository;
import com.gdsc.oauth2example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, Long userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        Post post = Post.builder()
                .author(user.getName())
                .user(user)
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .build();
        postRepository.save(post);
        return post.toDto();
    }


    @Transactional(readOnly = true)
    public PostResponseDto findPostById(Long postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        return post.toDto();
    }

}
