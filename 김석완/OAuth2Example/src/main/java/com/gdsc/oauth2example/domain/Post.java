package com.gdsc.oauth2example.domain;

import com.gdsc.oauth2example.dto.PostRequestDto;
import com.gdsc.oauth2example.dto.PostResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "POST_TITLE", nullable = false)
    private String title;

    @Column(name = "POST_CONTENT", nullable = false)
    private String content;

    @Column(name = "POST_AUTHOR")
    private String author;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Post(String title, String content, String author, User user){
        this.title = title;
        this.content = content;
        this.author = author;
        this.user = user;
    }

    public PostResponseDto toDto(){
        PostResponseDto postResponseDto = PostResponseDto.builder()
                .title(this.getTitle())
                .content(this.getContent())
                .build();
        return postResponseDto;
    }
}
