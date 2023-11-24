package com.gdsc.oauth2example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class OAuth2ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2ExampleApplication.class, args);
    }

}
