package com.microdaway.xypt.config;

import com.microdaway.xypt.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器 登录接口和注册接口不拦截
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/user/register", "/user/login");
    }
}
