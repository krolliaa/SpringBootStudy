package com.zwm.springbootstudy.handler.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        //定义拦截路径
        String[] addPathPatterns = {"/user/**"};
        //定义不拦截的路径
        String[] excludePathPatterns = {"/user/login", "/user/error"};
        //注册拦截器 ---> 首先你得有一个拦截器：创建拦截器 SpringBootInterceptor
        registry.addInterceptor(new SpringBootInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
