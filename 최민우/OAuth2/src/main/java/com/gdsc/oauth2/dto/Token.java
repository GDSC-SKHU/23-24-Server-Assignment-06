package com.gdsc.oauth2.dto;

import com.google.gson.annotations.SerializedName; // 따로 추가
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Token {
    @SerializedName("access_token")
    private String accessToken;
}
