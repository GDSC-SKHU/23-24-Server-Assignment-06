package com.gdsc.noticeservice.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private String id;
    // 작성자의 이름
    private String name;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("first_name")
    private String firstName;
    // 작성자의 이메일
    private String email;
    @SerializedName("verified_email")
    private Boolean verifiedEmail;
    // 작성자의 비밀번호
    private String pictureUrl;

    private List<PostDto> postDtos = new ArrayList<>();

}
