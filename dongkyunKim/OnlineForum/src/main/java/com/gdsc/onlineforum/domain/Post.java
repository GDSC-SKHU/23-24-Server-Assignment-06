package com.gdsc.onlineforum.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseTime{

    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "POST_TITLE", length = 500, nullable = false)
    private String title;

    @Column(name = "POST_CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "POST_AUTHOR")
    private String author;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void saveAuthor(String author) {
        this.author = author;
    }

}
