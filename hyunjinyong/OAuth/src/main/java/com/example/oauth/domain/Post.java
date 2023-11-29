package com.example.oauth.domain;

import com.example.oauth.dto.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postId;

    @Column(name = "POST_WRITER", nullable = false)
    String writer;
    @Column(name = "POST_TITLE", nullable = false)
    String title;
    @Column(name = "POST_CONTENT", nullable = false)
    String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    User user;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void saveWriter(User user){
        this.writer = user.getName();
    }

}
