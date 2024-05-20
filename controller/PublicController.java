package com.example.interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {  // 아무 사용자나 허용

    @GetMapping("/hello")
    public String hello() {
        return "public hello";
    }
}
