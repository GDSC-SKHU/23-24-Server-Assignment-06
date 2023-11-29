package com.gdsc.oauth2example.domain;

import com.gdsc.oauth2example.execption.InvalidContentException;
import com.gdsc.oauth2example.execption.InvalidTitleException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    private Post(String title, String content, String author, User user){
        this.title = title;
        this.content =content;
        this.author = author;
        this.user = user;
    }


    public void update(Post post){
        validateTitle(post.getTitle());
        validateContent(post.getContent());
        validateAuthor(post.getAuthor());

        this.title = post.title;
        this.content = post.content;
        this.author = post.author;
    }

    private void validateTitle(String title){
        if(title.isEmpty()) {
            throw new InvalidTitleException("제목은 1자 이상 입력하셔야 합니다.");
        }
    }
    private void validateContent(String content){
        if(content.isEmpty())
            throw new InvalidContentException("내용은 1자 이상 입력하셔야 합니다.");
    }
    private void validateAuthor(String author){
        if(author.isEmpty())

            throw new InvalidContentException("작성자는 1자 이상 입력하셔야 합니다.");
    }
}
