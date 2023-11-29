package com.gdsc.onlineforum.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;

@Data
@Getter
public class UserInformationDto {

    private Long userId;

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