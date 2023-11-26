package com.gdsc.oauth2assignment.controller;

import com.gdsc.oauth2assignment.dto.Token;
import com.gdsc.oauth2assignment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login/oauth2")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("code/google")
    public Token googleCallback(@RequestParam(name = "code") String code) {
        String googleAccessToken = authService.getGoogleAccessToken(code);
        return loginOrSignup(googleAccessToken);
    }

    public Token loginOrSignup(String googleAccessToken) {
        return authService.loginOrSignUp(googleAccessToken);
    }
}
