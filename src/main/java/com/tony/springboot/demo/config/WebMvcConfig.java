package com.tony.springboot.demo.config;

import com.tony.springboot.demo.interceptor.AllDenyInterceptor;
import com.tony.springboot.demo.interceptor.ApiSignatureCheckInterceptor;
import com.tony.springboot.demo.interceptor.PermissionCheckInterceptor;
import com.tony.springboot.demo.service.SysUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    @Lazy
    private SysUserService sysUserService;

    @Resource
    @Lazy
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    @Lazy
    private BizConfig systemConfig;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

        List<String> excludePatterns = Arrays.asList("/info", "/common/**", "/v3/**", "/swagger-ui/**", "/swagger-resources/**");

        interceptorRegistry
                .addInterceptor(new AllDenyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePatterns)
                .excludePathPatterns("/api/**", "/admin/**")
                .order(-100);

        // 拦截所有 /** 的访问地址
        interceptorRegistry
                .addInterceptor(new PermissionCheckInterceptor(sysUserService, redisTemplate))
                .addPathPatterns("/admin/**")
                .excludePathPatterns(excludePatterns)
                .order(0);

        interceptorRegistry
                .addInterceptor(new ApiSignatureCheckInterceptor(systemConfig))
                .addPathPatterns("/api/**")
                .excludePathPatterns(excludePatterns)
                .order(1);
    }

    /**
     * 解决前端跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedMethods("*");
    }
}