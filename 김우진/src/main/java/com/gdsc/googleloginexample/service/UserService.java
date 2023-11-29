package com.gdsc.googleloginexample.service;

import com.gdsc.googleloginexample.domain.Role;
import com.gdsc.googleloginexample.domain.User;
import com.gdsc.googleloginexample.dto.Token;
import com.gdsc.googleloginexample.dto.UserInfo;
import com.gdsc.googleloginexample.exception.ErrorCode;
import com.gdsc.googleloginexample.exception.model.CustomException;
import com.gdsc.googleloginexample.exception.model.NotFoundException;
import com.gdsc.googleloginexample.jwt.TokenProvider;
import com.gdsc.googleloginexample.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.security.Principal;
import java.util.Map;

@Service
public class UserService {

    private final String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private final String GOOGLE_CLIENT_ID;
    private final String GOOGLE_CLIENT_SECRET;
    private final String GOOGLE_REDIRECT_URI = "http://localhost:8080/api/oauth2/callback/google";

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final GoogleService googleService;

    public UserService(UserRepository userRepository, TokenProvider tokenProvider, GoogleService googleService) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.googleService = googleService;
        this.GOOGLE_CLIENT_ID = googleService.getGoogleClientId();
        this.GOOGLE_CLIENT_SECRET = googleService.getGoogleClientSecret();
    }

    public String getGoogleAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = Map.of(
                "code", code,
                "scope", "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email",
                "client_id", GOOGLE_CLIENT_ID,
                "client_secret", GOOGLE_CLIENT_SECRET,
                "redirect_uri", GOOGLE_REDIRECT_URI,
                "grant_type", "authorization_code"
        );

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_URL, params, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();

            return gson.fromJson(json, Token.class)
                    .getAccessToken();
        }

        throw new CustomException(ErrorCode.FAILED_GET_TOKEN_EXCEPTION,
                ErrorCode.FAILED_GET_TOKEN_EXCEPTION.getMessage());
    }

    public Token loginOrSignUp(String googleAccessToken) {
        UserInfo userInfo = getUserInformation(googleAccessToken);

        if (!userInfo.getVerifiedEmail()) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_EMAIL_EXCEPTION,
                    ErrorCode.UNAUTHORIZED_EMAIL_EXCEPTION.getMessage());
        }

        User user = userRepository.findByEmail(userInfo.getEmail())
                .orElseGet(() -> userRepository.save(User.builder()
                        .email(userInfo.getEmail())
                        .name(userInfo.getName())
                        .pictureUrl(userInfo.getPictureUrl())
                        .role(Role.ROLE_USER)
                        .build())
                );

        return tokenProvider.createToken(user);
    }

    public UserInfo getUserInformation(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();
            return gson.fromJson(json, UserInfo.class);
        }

        throw new CustomException(ErrorCode.VALIDATION_REQUEST_FAIL_USERINFO_EXCEPTION,
                ErrorCode.VALIDATION_REQUEST_FAIL_USERINFO_EXCEPTION.getMessage());
    }

    public User test(Principal principal) {
        Long id = Long.parseLong(principal.getName());

        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ID_EXCEPTION,
                        ErrorCode.NOT_FOUND_ID_EXCEPTION.getMessage()));
    }
}
