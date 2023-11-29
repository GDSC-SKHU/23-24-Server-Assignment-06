package com.gdsc.oauthassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OAuthAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthAssignmentApplication.class, args);
    }

}
