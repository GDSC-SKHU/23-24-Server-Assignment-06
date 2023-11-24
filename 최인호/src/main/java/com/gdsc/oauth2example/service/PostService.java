package com.gdsc.oauth2example.service;

import com.gdsc.oauth2example.domain.Post;
import com.gdsc.oauth2example.domain.User;
import com.gdsc.oauth2example.dto.PostResponseDto;
import com.gdsc.oauth2example.dto.PostRequestDto;
import com.gdsc.oauth2example.repository.PostRepository;
import com.gdsc.oauth2example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Transactional
    public void savePost(Long userId,PostRequestDto requestDTO){
        User user = userRepository.findById(userId)
                .orElseThrow();

        Post post = Post.builder()
                .author(requestDTO.getAuthor())
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .user(user) // 유저 값이 왜 들어가지 않을까?
                .build();
        postRepository.save(post);

    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostList(){
        List<Post> postList = postRepository.findAll();
        List<PostResponseDto> postDtoList = new ArrayList<>();

        for (Post post : postList){
            PostResponseDto postDto = PostResponseDto.builder()
                    .id(post.getId())
                    .author(post.getAuthor())
                    .content(post.getContent())
                    .userId(post.getUser().getId()) //user가 잘 저장이 안 된 듯..
                    .title(post.getTitle())
                    .createdDate(post.getCreatedDate()).build();
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    @Transactional
    public void updatePost(PostResponseDto postDto){
        Post post = postRepository.findById(postDto.getId()).orElseThrow();
        post.update(Post.builder()
                .author(postDto.getAuthor())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build());
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(PostResponseDto postDto){
        Post post = postRepository.findById(postDto.getId()).orElseThrow();
        postRepository.delete(post);
    }
}
