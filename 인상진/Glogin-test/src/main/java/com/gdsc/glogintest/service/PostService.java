package com.gdsc.glogintest.service;

import com.gdsc.glogintest.domain.Post;
import com.gdsc.glogintest.domain.User;
import com.gdsc.glogintest.dto.PostRequestDto;
import com.gdsc.glogintest.dto.PostResponseDto;
import com.gdsc.glogintest.repository.PostRepository;
import com.gdsc.glogintest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public String savePost(PostRequestDto postRequestDto){
        Post post = save(postRequestDto);
        postRepository.save(post);
        return "저장 완료!";
    }

    private Post save(PostRequestDto postRequestDto){
        User user = userRepository.findUserById(postRequestDto.getUserId()).orElseThrow(()->new IllegalArgumentException("정확한 회원을 입력하시오"));
        return Post.builder()
                .author(user.getName())
                .user(user)
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .build();
    }

    @Transactional(readOnly = true)
    public PostResponseDto findOne(Long postId){
        return findEntity(postId).toDto();
    }

    private Post findEntity(Long postId){
        Post post = postRepository.findPostById(postId).orElseThrow(()->new IllegalArgumentException("정확한 등록번호를 입력하시오"));
        return post;
    }

}
