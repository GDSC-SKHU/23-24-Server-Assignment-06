package com.gdsc.googleloginexample.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class GoogleService {

    private final String googleClientId;

    private final String googleClientSecret;

    public GoogleService(@Value("${google.client-id}") String googleClientId,
                         @Value("${google.client-secret}") String googleClientSecret) {
        this.googleClientId = googleClientId;
        this.googleClientSecret = googleClientSecret;
    }

}
