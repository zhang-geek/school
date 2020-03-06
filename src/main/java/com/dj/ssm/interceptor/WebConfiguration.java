package com.dj.ssm.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/30 21:34
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private PmsInterceptor pmsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //只拦截管理的入口页面
        InterceptorRegistration interceptorRegistration2 = registry.addInterceptor(pmsInterceptor);
        interceptorRegistration2.addPathPatterns("/user/toShow");
        interceptorRegistration2.addPathPatterns("/role/toShow");
        interceptorRegistration2.addPathPatterns("/resource/toShow");

    }
}
