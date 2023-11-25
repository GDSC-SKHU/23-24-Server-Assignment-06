package com.gdsc.oauth2assignment.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class UserInfo {
    private String id;
    private String iss; // (아마) 필수
    private String aud; // (아마) 필수
    private String sub; // (아마) 필수, 사용자의 식별자, sub 값은 변경 X
    private String email;
    @SerializedName("verified_email")
    private Boolean verifiedEmail;
    private String name;
    @SerializedName("given_name")
    private String givenName;
    @SerializedName("family_name")
    private String familyName;
    private String iat; // (아마) 필수
    private String exp; // (아마) 필수
}
