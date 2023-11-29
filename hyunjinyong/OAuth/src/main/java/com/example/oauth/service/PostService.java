package com.example.oauth.service;

import com.example.oauth.domain.Post;
import com.example.oauth.dto.PostInfoDto;
import com.example.oauth.dto.PostSaveDto;
import com.example.oauth.error.Message;
import com.example.oauth.repository.PostRepository;
import com.example.oauth.repository.UserRepository;
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
    public PostInfoDto savePost(PostSaveDto postSaveDto, Principal principal) {
        Post post = postSaveDto.toEntity();
        Long userId = Long.valueOf(principal.getName());
        //작성자 정보 저장
        post.saveWriter(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(Message.NotUser)));
        postRepository.save(post);

        return PostInfoDto.makeOf(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getWriter());
    }

    public PostInfoDto searchPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException(Message.NoPost));
        return PostInfoDto.makeOf(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getWriter()
        );
    }
}
