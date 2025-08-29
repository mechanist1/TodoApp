package com.example.jwttodoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JwtTodoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtTodoAppApplication.class, args);
    }

}
