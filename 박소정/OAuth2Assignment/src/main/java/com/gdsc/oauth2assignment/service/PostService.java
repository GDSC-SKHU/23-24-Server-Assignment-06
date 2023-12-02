package com.gdsc.oauth2assignment.service;

import com.gdsc.oauth2assignment.domain.Post;
import com.gdsc.oauth2assignment.domain.User;
import com.gdsc.oauth2assignment.dto.PostResDto;
import com.gdsc.oauth2assignment.dto.PostSaveReqDto;
import com.gdsc.oauth2assignment.repository.PostRepository;
import com.gdsc.oauth2assignment.repository.UserRepository;
import java.security.Principal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String createPost(PostSaveReqDto requestDto, Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        Post post = requestDto.toEntity(user);
        postRepository.save(post);
        return "저장 성공!";
    }

    public PostResDto findPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
        return new PostResDto(post);
    }
}
