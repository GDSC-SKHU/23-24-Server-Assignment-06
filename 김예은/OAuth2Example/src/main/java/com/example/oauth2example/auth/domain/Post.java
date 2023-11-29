package com.example.oauth2example.auth.domain;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "POST_NAME", length = 500, nullable = false)
    private String name;

    @Column(name = "POST_AUTHROR")
    private String author;

    @Column(name = "POST_CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

   @Builder
    private Post(Long postId, String author, String name, String content, User user){
        this.postId = postId;
        this.author = author;
        this.name = name;
        this.content =content;
        this.user = user;

    }
}