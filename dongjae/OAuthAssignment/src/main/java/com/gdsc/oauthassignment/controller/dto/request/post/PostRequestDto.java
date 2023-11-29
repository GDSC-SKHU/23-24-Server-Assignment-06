package com.gdsc.oauthassignment.controller.dto.request.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@Setter
public class PostRequestDto {

    @NotBlank
    private String title;
    @NotNull
    private Long ornamentId;
    @NotBlank
    private String content;

}
