package com.gdsc.oauth2assignment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "POST_TITLE")
    private String title;

    @Column(name = "POST_CONTENT")
    private String content;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    private Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
