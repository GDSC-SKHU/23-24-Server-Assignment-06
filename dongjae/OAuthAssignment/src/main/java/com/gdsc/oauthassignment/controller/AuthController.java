package com.gdsc.oauthassignment.controller;

import com.gdsc.oauthassignment.common.dto.BaseResponse;
import com.gdsc.oauthassignment.config.resolver.UserId;
import com.gdsc.oauthassignment.controller.dto.response.auth.SignInResponseDto;
import com.gdsc.oauthassignment.controller.dto.response.auth.TokenResponseDto;
import com.gdsc.oauthassignment.exception.SuccessCode;
import com.gdsc.oauthassignment.oauth2.userInfo.OAuth2UserInfo;
import com.gdsc.oauthassignment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final HttpSession httpSession;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<SignInResponseDto> signIn() {
        OAuth2UserInfo userInfo = (OAuth2UserInfo) httpSession.getAttribute("oAuth2UserInfo");
        String provider = (String) httpSession.getAttribute("provider");
        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS, authService.signIn(userInfo, provider));
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<TokenResponseDto> reissueToken(@RequestHeader String refreshToken) {
        return BaseResponse.success(SuccessCode.RE_ISSUE_TOKEN_SUCCESS, authService.issueToken(refreshToken));
    }

    @PostMapping("/sign-out")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse signOut(@UserId Long userId) {
        authService.signOut(userId);
        return BaseResponse.success(SuccessCode.SIGNOUT_SUCCESS);
    }

}