package com.gdsc.oauthassignment.domain.user;

import com.gdsc.oauthassignment.domain.BaseEntity;
import com.gdsc.oauthassignment.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@DynamicInsert // null값이 아닌 필드만을 대상으로 SQL INSERT 문을 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String socialId;

    @Column(nullable = true)
    private String refreshToken;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;


    @JsonIgnore
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> postList;

    @Builder
    public User(String userName, String socialId, SocialType socialType) {
        this.userName = userName;
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}