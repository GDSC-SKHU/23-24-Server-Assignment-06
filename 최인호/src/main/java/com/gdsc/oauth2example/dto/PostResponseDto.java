package com.gdsc.oauth2example.dto;

import com.gdsc.oauth2example.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private Long userId;
    private LocalDateTime createdDate;

    public Post toEntity() {

        return Post.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .createdDate(createdDate)
                .build();
    }

    @Builder
    public PostResponseDto(Long id, String author, String title, String content, Long userId, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdDate = createdDate;
    }
}
