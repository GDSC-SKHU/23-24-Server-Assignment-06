package com.gdsc.oauthassignment.domain.post;

import com.gdsc.oauthassignment.domain.BaseEntity;
import com.gdsc.oauthassignment.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Getter
@Table(name = "GUEST_BOOK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Long ornamentId;

    @Builder
    public Post(String title, String content, User user, Long ornamentId) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.ornamentId = ornamentId;
    }
}
