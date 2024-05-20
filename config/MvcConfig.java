package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor  // final 객체들을 생성자에서 주입 받을 수 있게 해줌.
public class MvcConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
        //registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");  이렇게 원하는 주소 지정 가능
        // 밑에 계속 interceptor 추가 등록 가능, 등록하는 순서대로 검사 진행
    }
}
