package com.tony.springboot.demo.interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.StrUtil;
import com.tony.springboot.demo.constant.Codes;
import com.tony.springboot.demo.constant.Constants;
import com.tony.springboot.demo.constant.RedisKeys;
import com.tony.springboot.demo.context.UserConext;
import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.model.bo.UserBO;
import com.tony.springboot.demo.service.SysUserService;
import com.tony.springboot.demo.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@Slf4j
public class PermissionCheckInterceptor implements HandlerInterceptor {

    private SysUserService sysUserService;
    private RedisTemplate<String, Object> redisTemplate;

    public PermissionCheckInterceptor(SysUserService sysUserService, RedisTemplate<String, Object> redisTemplate) {
        this.sysUserService = sysUserService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constants.TOKEN);
        if (StrUtil.isBlank(token)) {
            ResponseUtil.writeJson(R.error(Codes.UNAUTHORIZED, "缺少`token`参数"), response);
            return false;
        }
        UserBO userBO = (UserBO) redisTemplate.opsForValue().get(RedisKeys.TOKEN_PREFIX + token);
        if (userBO == null) {
            ResponseUtil.writeJson(R.error(Codes.UNAUTHORIZED, "无效token或已失效"), response);
            return false;
        }
        boolean authorization = authorization(request, userBO.getUserId());
        if(!authorization) {
            ResponseUtil.writeJson(R.error(Codes.UNAUTHORIZED, "接口无权限"), response);
            return false;
        }
        UserConext.setUser(userBO);
        return true;
    }

    private boolean authorization(HttpServletRequest request, Long userId) {
        String requestURI = request.getRequestURI();
        Set<String> userApis = sysUserService.queryUserApis(userId);
        if (CollUtil.isEmpty(userApis)) {
            return false;
        }
        for (String userApi : userApis) {
            boolean match = new AntPathMatcher().match(userApi, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserConext.clear();
    }


}
