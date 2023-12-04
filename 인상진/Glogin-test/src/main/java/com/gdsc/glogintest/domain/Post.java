package com.gdsc.glogintest.domain;

import com.gdsc.glogintest.dto.PostRequestDto;
import com.gdsc.glogintest.dto.PostResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String Author;

    @Column(nullable= false)
    private String title;

    @Column(nullable = false, length = 1500)
    private String content;

    @Column(name = "CREATE_AT", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name = "UPDATE_AT")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @Builder
    public Post(String title, String content, User user,String author){
        this.title=title;
        this.content=content;
        this.user=user;
        this.Author=author;
    }


    public PostResponseDto toDto() {
        PostResponseDto postResponseDto = PostResponseDto.builder()
                .Author(this.getAuthor())
                .title(this.getTitle())
                .content(this.getContent())
                .createAt(this.getCreateAt())
                .updateAt(this.getUpdateAt())
                .build();
        return postResponseDto;
    }
}
