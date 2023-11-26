package com.gdsc.skhu.googleloginbulletinboard.dto;

import com.gdsc.skhu.googleloginbulletinboard.domain.Role;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserInfo {
    private Long id;
    private String email;
    @SerializedName("verified_email")
    private Boolean verifiedEmail;
    private String name;
    @SerializedName("given_name")
    private String givenName;
    @SerializedName("family_name")
    private String familyName;
    @SerializedName("picture")
    private String pictureUrl;
    private String locale;
}
