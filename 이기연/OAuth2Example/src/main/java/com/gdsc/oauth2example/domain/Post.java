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
public class Post {
    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="POST_USER_NAME")
    private String userName;

    @Column(name = "POST_TITLE", nullable = false)
    private String title;

    @Column(name = "POST_BODY", nullable = false)
    private String body;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @Builder
    public Post(String userName, String title, String body, User user){
        this.userName=userName;
        this.title=title;
        this.body=body;
        this.user=user;
    }

    public PostResponseDto toDto(){
        PostResponseDto postResponseDto = PostResponseDto.builder()
                .userName(this.getUserName())
                .title(this.getTitle())
                .body(this.getBody())
                .build();
        return postResponseDto;
    }
}
