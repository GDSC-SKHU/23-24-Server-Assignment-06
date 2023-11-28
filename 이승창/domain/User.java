package com.gdsc.noticeservice.domain;

import com.gdsc.noticeservice.dto.UserDto;
import com.gdsc.noticeservice.dto.PostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_PICTURE_URL")
    private String pictureUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;

    // 회원으로 회원이 올린 글 확인하기 / 연관 관계의 주인이 아닌 것은 조회만 가능.
    @OneToMany(mappedBy = "user")
    @Column(name = "USER_POST")
    private List<Post> posts =new ArrayList<>();

}
