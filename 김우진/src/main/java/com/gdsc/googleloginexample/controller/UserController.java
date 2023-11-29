package com.gdsc.googleloginexample.controller;

import com.gdsc.googleloginexample.dto.Token;
import com.gdsc.googleloginexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth2")
public class UserController {
    private final UserService userService;

    @GetMapping("/callback/google")
    public Token googleCallback(@RequestParam(name = "code") String code) {
        String googleAccessToken = userService.getGoogleAccessToken(code);

        return loginOrSignup(googleAccessToken);
    }

    public Token loginOrSignup(String googleAccessToken) {
        return userService.loginOrSignUp(googleAccessToken);
    }
}
