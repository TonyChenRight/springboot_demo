package com.tony.springboot.demo.interceptor;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.tony.springboot.demo.config.BizConfig;
import com.tony.springboot.demo.constant.Codes;
import com.tony.springboot.demo.constant.Headers;
import com.tony.springboot.demo.constant.SignatureParams;
import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.utils.ResponseUtil;
import com.tony.springboot.demo.utils.SignatureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class ApiSignatureCheckInterceptor implements HandlerInterceptor {

    private BizConfig bizConfig;

    public ApiSignatureCheckInterceptor(BizConfig bizConfig) {
        this.bizConfig = bizConfig;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String signature = request.getParameter(SignatureParams.SIGNATURE);
        if (StrUtil.isBlank(signature)) {
            ResponseUtil.writeJson(R.error(Codes.UNAUTHORIZED, "缺少参数`signature`"), response);
            return false;
        }
        String keyId = request.getParameter(SignatureParams.KEY_ID);
        if (StrUtil.isBlank(keyId)) {
            ResponseUtil.writeJson(R.error(Codes.UNAUTHORIZED, "缺少参数`keyId`"), response);
            return false;
        }
        String secret = bizConfig.getApiSignatureMap().get(keyId);
        if (StrUtil.isBlank(secret)) {
            ResponseUtil.writeJson(R.error(Codes.UNAUTHORIZED, "无效参数`keyId`"), response);
            return false;
        }
        String timestamp = request.getParameter(SignatureParams.TIMESTAMP);
        if (StrUtil.isBlank(timestamp)) {
            ResponseUtil.writeJson(R.error(Codes.UNAUTHORIZED, "缺少参数`timestamp`"), response);
            return false;
        }
        DateTime dateTime = DateUtil.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (Math.abs(dateTime.between(DateTime.now(), DateUnit.SECOND)) > 300L) {
            ResponseUtil.writeJson(R.error(Codes.UNAUTHORIZED, "参数`timestamp`已超出"), response);
            return false;
        }

        String method = request.getMethod();
        String host = getRealHost(request);
        String path = request.getRequestURI();

        String calcSignature = SignatureUtil.calcSignature(method, host, path, secret, getParams(request));
        if (!Objects.equals(calcSignature, signature)) {
            ResponseUtil.writeJson(R.error(Codes.UNAUTHORIZED, "签名不匹配"), response);
            return false;
        }
        return true;
    }

    private String getRealHost(HttpServletRequest request) {
        String forwardHost = request.getHeader(Headers.FORWARD_HOST);
        String remoteHost = request.getRemoteHost();
        return StrUtil.isNotBlank(forwardHost) ? forwardHost : remoteHost;
    }

    private Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> result =new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            result.put(key, value);
        }
        return result;
    }
}
