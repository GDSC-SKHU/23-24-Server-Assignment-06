package com.gdsc.oauth2assignment.dto;

import com.gdsc.oauth2assignment.domain.Post;
import com.gdsc.oauth2assignment.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveReqDto {
    private String title;
    private String content;

    public Post toEntity(User user) {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .user(user)
                .build();
    } // 클라이언트로부터 받은 데이터로 엔티티를 생성하고, 이를 데이터베이스에 저장
}
