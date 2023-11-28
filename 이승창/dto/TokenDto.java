package com.gdsc.noticeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.google.gson.annotations.SerializedName;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    @SerializedName("access_token")
    private String accessToken;
}
