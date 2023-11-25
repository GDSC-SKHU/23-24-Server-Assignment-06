package com.gdsc.googleloginexample.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String postSubject;
    private String postDetail;
}
