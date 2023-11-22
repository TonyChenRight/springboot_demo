package com.tony.springboot.demo.interceptor;


import com.tony.springboot.demo.constant.Codes;
import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AllDenyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResponseUtil.writeJson(R.error(Codes.UNAUTHORIZED, "无权限"), response);
        return false;
    }
}
