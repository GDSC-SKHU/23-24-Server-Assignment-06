package com.gdsc.glogintest.controller;

import com.gdsc.glogintest.dto.Token;
import com.gdsc.glogintest.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth2")
public class AuthController {

    private final AuthService authService;

    @GetMapping("callback/google")
    public Token googleCallback(@RequestParam(name = "code") String code) {
        String googleAccessToken = authService.getGoogleAccessToken(code);
        return loginOrSignup(googleAccessToken);
    }

    public Token loginOrSignup(String googleAccessToken) {
        return authService.loginOrSignUp(googleAccessToken);
    }

}
