package com.gdsc.onlineforum.service;

import com.gdsc.onlineforum.domain.Post;

import com.gdsc.onlineforum.domain.User;
import com.gdsc.onlineforum.dto.response.PostDeleteResponseDto;
import com.gdsc.onlineforum.dto.response.PostResponseDto;
import com.gdsc.onlineforum.dto.request.PostCreateRequestDto;
import com.gdsc.onlineforum.dto.request.PostUpdateRequestDto;
import com.gdsc.onlineforum.dto.response.PostCreateResponseDto;
import com.gdsc.onlineforum.dto.response.PostUpdateResponseDto;
import com.gdsc.onlineforum.message.ErrorMessage;
import com.gdsc.onlineforum.repository.PostRepository;
import com.gdsc.onlineforum.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostCreateResponseDto createPost(PostCreateRequestDto postCreateRequestDto, Principal principal) {

        Long userId = Long.valueOf(principal.getName());
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException(ErrorMessage.NOT_FOUND_USER_EXCEPTION_MESSAGE));

        Post post = postCreateRequestDto.toEntity();

        post.saveAuthor(user.getName());
        postRepository.save(post);

        return PostCreateResponseDto.of(post.getPostId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getCreateDate());
    }

    public List<PostResponseDto> getAllPosts() {

        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> postResponseDto = new ArrayList<>();

        for (Post post : posts) {
            postResponseDto.add(
                    PostResponseDto.of(post.getPostId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getCreateDate(), post.getUpdateDate())
            );
        }

        return postResponseDto;
    }

    @Transactional
    public PostUpdateResponseDto updatePost(Long postId, PostUpdateRequestDto postUpdateRequestDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException(ErrorMessage.NOT_FOUND_USER_EXCEPTION_MESSAGE));

        post.updatePost(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContent());

        return PostUpdateResponseDto.of(post.getPostId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getCreateDate(), post.getUpdateDate());
    }

    @Transactional
    public PostDeleteResponseDto deletePost(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException(ErrorMessage.NOT_FOUND_USER_EXCEPTION_MESSAGE));

        postRepository.delete(post);

        return PostDeleteResponseDto.of(postId);
    }


}