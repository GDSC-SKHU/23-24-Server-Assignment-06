package com.gdsc.noticeservice.service;

import com.gdsc.noticeservice.domain.Post;
import com.gdsc.noticeservice.dto.PostDto;
import com.gdsc.noticeservice.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    // CREATE - 게시글 작성
    @Transactional
    public String createPost(PostDto postDto){
            Post post = Post.builder()
                    .title(postDto.getTitle())
                    .content(postDto.getContent())
                    .author(postDto.getAuthor())
                    .build();
            postRepository.save(post);
            return "게시글 작성 완료!";
    }

    // READ - 게시글 불러오기
    @Transactional
    public Post findPostById(Long id){
        return postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 작성자"));
    }
    @Transactional
    public Post findPostByTitle(String title){
        return postRepository.findPostByTitle(title)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 작성자"));
    }
    @Transactional
    public PostDto findPostByTitleFromConsumerDto(String title){
        return findPostByTitle(title).makePostToDto();
    }

    // DELETE
    @Transactional
    public String deletePostById(PostDto postDto){
        postRepository.deleteById(postDto.getId());
        return "삭제 완료";
    }
}
