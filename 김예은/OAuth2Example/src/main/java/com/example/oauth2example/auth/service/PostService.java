package com.example.oauth2example.auth.service;
import com.example.oauth2example.auth.domain.Post;
import com.example.oauth2example.auth.domain.User;
import com.example.oauth2example.auth.dto.PostRequest;
import com.example.oauth2example.auth.dto.PostResponse;
import com.example.oauth2example.auth.repository.PostRepository;
import com.example.oauth2example.auth.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Transactional
    public void createPost(Long userId,PostRequest requestDTO){
        User user = userRepository.findById(userId)
                .orElseThrow();

        Post post = Post.builder()
                .author(requestDTO.getAuthor())
                .name(requestDTO.getName())
                .content(requestDTO.getContent())
                .user(user)
                .build();
        postRepository.save(post);

    }

    @Transactional
    public List<PostResponse> readPost(){
        List<Post> readPost = postRepository.findAll();
        List<PostResponse> postDtoRead = new ArrayList<>();

        for (Post post :  readPost){
            PostResponse postDto = PostResponse.builder()
                    .postId(post.getPostId())
                    .author(post.getAuthor())
                    .name(post.getName())
                    .content(post.getContent())
                    .userId(post.getUser().getId())
                    .build();

            postDtoRead.add(postDto);
        }
        return postDtoRead;
    }

}