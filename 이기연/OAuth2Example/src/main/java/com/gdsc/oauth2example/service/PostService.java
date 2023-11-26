package com.gdsc.oauth2example.service;

import com.gdsc.oauth2example.domain.Post;
import com.gdsc.oauth2example.domain.User;
import com.gdsc.oauth2example.dto.PostRequestDto;
import com.gdsc.oauth2example.dto.PostResponseDto;
import com.gdsc.oauth2example.repository.PostRepository;
import com.gdsc.oauth2example.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, Long userId) {
        // 사용자 ID를 이용하여 사용자 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // Post 엔터티 생성
        Post newPost = Post.builder()
                .userName(user.getName())
                .title(postRequestDto.getTitle())
                .body(postRequestDto.getBody())
                .user(user)
                .build();

        // 생성된 게시글을 저장하고 PostResponseDto로 변환하여 반환
        Post savedPost = postRepository.save(newPost);
        return savedPost.toDto();
    }
    @Transactional
    public PostResponseDto findPostById(Long postId) {
        // PostRepository를 사용하여 게시글 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        return post.toDto();
    }

}

