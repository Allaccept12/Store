package com.example.store.config;

import com.example.store.common.error.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        if (url.contains("swagger") || url.contains("api-docs") || url.contains("webjars")) {
            return true;
        }

        if (session == null || session.getAttribute("loginSession") == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorResponse message = ErrorResponse.builder().message("로그인이 필요한 서비스 입니다.").build();
            String json = objectMapper.writeValueAsString(message);
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print(json);
            return false;
        }
        return true;
    }
}
