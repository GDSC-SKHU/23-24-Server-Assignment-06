package com.gdsc.oauth2example.service;

import com.gdsc.oauth2example.domain.Post;
import com.gdsc.oauth2example.domain.User;
import com.gdsc.oauth2example.dto.PostRequest;
import com.gdsc.oauth2example.repository.PostRepository;
import com.gdsc.oauth2example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Post save(PostRequest postRequest, Principal principal) {

        User user = userRepository.findById(Long.valueOf(principal.getName())).orElseThrow(() -> new IllegalStateException("user X"));
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
