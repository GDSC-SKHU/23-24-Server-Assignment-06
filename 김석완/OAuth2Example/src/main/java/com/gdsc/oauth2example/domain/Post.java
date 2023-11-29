package com.gdsc.oauth2example.domain;

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
@Builder
public class Post {
    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "POST_TITLE", nullable = false)
    private String title;

    @Column(name = "POST_CONTENT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    private Post(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

}
