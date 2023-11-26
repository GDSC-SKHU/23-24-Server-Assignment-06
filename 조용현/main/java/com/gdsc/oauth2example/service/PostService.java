package com.gdsc.oauth2example.service;

import com.gdsc.oauth2example.domain.Post;
import com.gdsc.oauth2example.domain.User;
import com.gdsc.oauth2example.dto.PostRequest;
import com.gdsc.oauth2example.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post save(PostRequest postRequest, User user) {

        Post post = Post.builder()
                .content(postRequest.content())
                .user(user)
                .build();

        return postRepository.save(post);
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
