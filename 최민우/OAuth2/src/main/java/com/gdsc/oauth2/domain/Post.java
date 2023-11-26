package com.gdsc.oauth2.domain;

import com.gdsc.oauth2.dto.PostResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "POST_TITLE", nullable = false)
    private String title;

    @Column(name = "POST_CONTENT", nullable = false)
    private String content;

    @Column(name = "POST_WRITER", nullable = false)
    private String writer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    User user;

    @Builder
    public Post(String title, String content, String writer, User user){
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.user = user;
    }

    public PostResponse toDto(){
        PostResponse postResponse = PostResponse.builder()
                .title(this.getTitle())
                .content(this.getContent())
                .writer(this.getWriter())
                .build();
        return postResponse;
    }
}
