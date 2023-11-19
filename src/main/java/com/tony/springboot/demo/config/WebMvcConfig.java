package com.tony.springboot.demo.config;

import com.tony.springboot.demo.interceptor.PermissionInterceptor;
import com.tony.springboot.demo.service.SysUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    @Lazy
    private SysUserService sysUserService;

    @Resource
    @Lazy
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        // 拦截所有 /admin/** 的访问地址
        interceptorRegistry.addInterceptor(new PermissionInterceptor(sysUserService, redisTemplate)).addPathPatterns("/admin/**");
    }
}