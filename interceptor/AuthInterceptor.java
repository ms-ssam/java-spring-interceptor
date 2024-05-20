package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component  // spring에서 관리
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        log.info("request url : {}", url);

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI()).query(request.getQueryString())
                .build().toUri();

        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        // 서버는 public으로 동작. 단, Auth annotation을 가진 요청의 경우 쿠키나 세션을 검사
        if(hasAnnotation) {
            // 권한 체크
            String query = uri.getQuery();
            if(query.equals("name=steve"))  // 쿠키나 세션 검사 대신 쿼리 검사로 대체
                return true;
            throw new AuthException();
        }
        return true;  // false면 요청이 interceptor에서 controller로 넘어가지 않음. interceptor에서 바로 반환
    }

    private boolean checkAnnotation(Object handler, Class clazz) {
        // resource에 관련된 요청의 경우 통과
        if(handler instanceof ResourceHttpRequestHandler)
            return true;

        // 특정 Annotation(Auth)을 가지고 있을 경우 통과
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(handlerMethod.getMethodAnnotation(clazz) != null || handlerMethod.getBeanType().getAnnotation(clazz) != null)
            return true;

        // 그 외의 경우들은 통과 X
        return false;
    }
}
