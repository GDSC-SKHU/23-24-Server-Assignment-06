package com.gdsc.googleloginexample.dto;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Token {

    @SerializedName("access_token") // json과 java사이의 매핑
    private String accessToken;
}
