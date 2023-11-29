package com.gdsc.googleloginexample.controller;

import com.gdsc.googleloginexample.domain.User;
import com.gdsc.googleloginexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @GetMapping("/test")
    public User test(Principal principal) {
        return userService.test(principal);
    }
}
