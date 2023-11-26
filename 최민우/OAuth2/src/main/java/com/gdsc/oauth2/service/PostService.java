package com.gdsc.oauth2.service;

import com.gdsc.oauth2.domain.Post;
import com.gdsc.oauth2.domain.User;
import com.gdsc.oauth2.dto.PostRequest;
import com.gdsc.oauth2.dto.PostResponse;
import com.gdsc.oauth2.repository.PostRepository;
import com.gdsc.oauth2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponse createPost(PostRequest postRequest, Long userId) {
        // 사용자 ID를 이용하여 사용자 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // Post 엔터티 생성
        Post newPost = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .writer(user.getName())
                .user(user)
                .build();

        // 생성된 게시글을 저장하고 PostResponseDto로 변환하여 반환
        Post savedPost = postRepository.save(newPost);
        return savedPost.toDto();
    }
    @Transactional
    public PostResponse findPostById(Long postId) {
        // PostRepository를 사용하여 게시글 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        return post.toDto();
    }
}