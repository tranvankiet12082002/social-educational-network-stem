package com.trvankiet.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info =
        @Info(title = "Friend API", version = "1.0", description = "Documentation Friend API v1.0")
)
public class FriendServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendServiceApplication.class, args);
    }
}
