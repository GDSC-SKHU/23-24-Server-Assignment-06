package com.gdsc.oauth2assignment.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Token {
    @SerializedName("id_token")
    private String idToken;
}
