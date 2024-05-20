package com.example.interceptor.controller;

import com.example.interceptor.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
@Auth  // Filter와 달리 spring context에 등록되어 있기 때문에 annotation으로 다루기 가능
@Slf4j
public class PrivateController {  // 내부 사용자 or 인증된 사용자만

    @GetMapping("/hello")
    public String hello() {
        log.info("private hello controller");
        return "private hello";
    }

}
