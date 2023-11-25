package com.gdsc.googleloginexample.service;

import com.gdsc.googleloginexample.domain.Post;
import com.gdsc.googleloginexample.domain.User;
import com.gdsc.googleloginexample.dto.Request.PostCreateRequestDto;
import com.gdsc.googleloginexample.dto.Request.PostUpdateRequestDto;
import com.gdsc.googleloginexample.dto.Response.PostCreateResponseDto;
import com.gdsc.googleloginexample.dto.Response.PostDeleteResponseDto;
import com.gdsc.googleloginexample.dto.Response.PostResponseDto;
import com.gdsc.googleloginexample.exception.ErrorCode;
import com.gdsc.googleloginexample.exception.model.BadRequestException;
import com.gdsc.googleloginexample.exception.model.CustomException;
import com.gdsc.googleloginexample.exception.model.NotFoundException;
import com.gdsc.googleloginexample.exception.model.UnauthorizedException;
import com.gdsc.googleloginexample.jwt.TokenProvider;
import com.gdsc.googleloginexample.repository.PostRepository;
import com.gdsc.googleloginexample.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.PrinterGraphics;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Transactional
    public PostCreateResponseDto createPost(Principal principal, PostCreateRequestDto postCreateRequestDto) { // userid 추출 받은걸로 post 작성하기
        Long userId = Long.parseLong(principal.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID_EXCEPTION,
                        ErrorCode.NOT_FOUND_ID_EXCEPTION.getMessage()));


        if (postRepository.existsByPostSubject(postCreateRequestDto.getPostSubject())) {
            throw new BadRequestException(ErrorCode.ALREADY_EXIST_SUBJECT_EXCEPTION,
                    ErrorCode.ALREADY_EXIST_SUBJECT_EXCEPTION.getMessage());
        }

        Post post = postCreateRequestDto.toDto(user);
        post = postRepository.save(post);

        return PostCreateResponseDto.of(post);
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST_EXCEPTION,
                        ErrorCode.NOT_FOUND_POST_EXCEPTION.getMessage()));

        return PostResponseDto.of(
                post.getPostId(),
                post.getPostSubject(),
                post.getPostDetail()
        );
    }

    public List<PostResponseDto> getAllPostByUserId(Principal principal) { // 로그인한 사용자 본인이 작성한 전체 글 조회
        Long userId = Long.parseLong(principal.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID_EXCEPTION,
                        ErrorCode.NOT_FOUND_ID_EXCEPTION.getMessage()));

        List<Post> posts = postRepository.findByUser(user);
        List<PostResponseDto> result = new ArrayList<>();

        for(Post post : posts) {
            result.add(
                    PostResponseDto.of(
                            post.getPostId(),
                            post.getPostSubject(),
                            post.getPostDetail()
                    )
            );
        }

        return result;
    }

    @Transactional // 게시글 작성한 사용자와 요청한 사용자만이 게시글을 수정할 수 있음
    public PostResponseDto updatePost(Principal principal, Long postId, PostUpdateRequestDto postUpdateRequestDto) {
        Long userId = Long.parseLong(principal.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID_EXCEPTION,
                        ErrorCode.NOT_FOUND_ID_EXCEPTION.getMessage()));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST_EXCEPTION,
                        ErrorCode.NOT_FOUND_POST_EXCEPTION.getMessage()));

        if (!post.getUser().equals(user)) {
            throw new UnauthorizedException(ErrorCode.UNAUTHORIZED_INVALID_EXCEPTION,
                    ErrorCode.UNAUTHORIZED_INVALID_EXCEPTION.getMessage());
        }

        post.updatePost(postUpdateRequestDto.getPostSubject(), postUpdateRequestDto.getPostDetail());

        return PostResponseDto.of(
                post.getPostId(),
                post.getPostSubject(),
                post.getPostDetail()
        );
    }

    @Transactional // 게시글 작성한 사용자만이 글을 삭제할 수 있도록 함
    public PostDeleteResponseDto deletePost(Principal principal, Long postId) {
        Long userId = Long.parseLong(principal.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID_EXCEPTION,
                        ErrorCode.NOT_FOUND_ID_EXCEPTION.getMessage()));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST_EXCEPTION,
                        ErrorCode.NOT_FOUND_POST_EXCEPTION.getMessage()));

        if (!post.getUser().equals(user)) {
            throw new UnauthorizedException(ErrorCode.UNAUTHORIZED_INVALID_EXCEPTION,
                    ErrorCode.UNAUTHORIZED_INVALID_EXCEPTION.getMessage());
        }

        postRepository.delete(post);

        return PostDeleteResponseDto.of(post.getPostId());
    }

}
