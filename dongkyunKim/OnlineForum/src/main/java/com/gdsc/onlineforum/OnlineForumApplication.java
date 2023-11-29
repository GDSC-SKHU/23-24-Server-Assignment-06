package com.gdsc.onlineforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OnlineForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineForumApplication.class, args);
    }

}
