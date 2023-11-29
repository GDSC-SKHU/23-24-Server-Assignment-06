package com.gdsc.googleloginexample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "POST_SUBJECT", nullable = false)
    private String postSubject;

    @Column(name = "POST_DETAIL", nullable = false)
    private String postDetail;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public void updatePost(String postSubject, String postDetail) {
        this.postSubject = postSubject;
        this.postDetail = postDetail;
    }
}
