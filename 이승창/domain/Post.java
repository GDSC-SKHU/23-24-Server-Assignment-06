package com.gdsc.noticeservice.domain;

import com.gdsc.noticeservice.dto.PostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "POST_TITLE")
    private String title;

    @Column(name = "POST_CONTENT")
    private String content;

    @Column(name = "POST_AUTHOR")
    private String author;

    // 변동 가능한 확률이 높은 Post를 연관 관계의 주인으로 지정 / 외래키 지정 / 다대일 연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONSUMER_ID")
    private User user;

public PostDto makePostToDto(){
    if(user != null){
        return PostDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .author(user.getName())
                .build();
        }
        return PostDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
    public void postUpdate(Post post){
        this.content = post.content;
        this.title = post.title;
        this.user = post.user;
    }
}
