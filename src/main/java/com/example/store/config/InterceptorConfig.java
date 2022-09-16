package com.example.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AuthCheckInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/account/login","account/logout","/account/register","/products/**");
    }
}
