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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // CREATE
    @Transactional
    public void createPost(Long userId, PostRequest requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .writer(requestDto.getWriter())
                .user(user)
                .build();
        postRepository.save(post);
    }

    // READ
    public List<PostResponse> readPost() {
        List<Post> readPost = postRepository.findAll();
        List<PostResponse> postDtoRead = new ArrayList<>();

        for (Post post : readPost) {
            PostResponse postDto = PostResponse.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .writer(post.getWriter())
                    .userId(post.getUser().getId())
                    .build();

            postDtoRead.add(postDto);
        }
        return postDtoRead;
    }
}