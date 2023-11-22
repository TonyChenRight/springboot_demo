package com.tony.springboot.demo.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.tony.springboot.demo.constant.Headers;
import com.tony.springboot.demo.constant.SignatureParams;
import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.model.vo.user.SysUserPageVO;
import com.tony.springboot.demo.utils.SignatureUtil;
import io.swagger.models.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TestService {

    private static final String USER_PAGE_URL = "http://127.0.0.1:8080/api/user/page";
    private static final String KEY_ID = "417c4f3f30d34043";
    private static final String SECRET = "5d940d7bb69d428eaf813a243591aba3";
    public R userPageByApi(SysUserPageVO queryVO) {
        try {
            Map<String, String> params = JSON.parseObject(JSON.toJSONString(queryVO), new TypeReference<Map<String, String>>() {});
            // 追加签名参数
            params.put(SignatureParams.KEY_ID, KEY_ID);
            params.put(SignatureParams.TIMESTAMP, DateUtil.format(DateTime.now(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            URL url = new URL(USER_PAGE_URL);
            String signature = SignatureUtil.calcSignature(HttpMethod.GET.name(), url.getHost(), url.getPath(), SECRET, params);
            params.put(SignatureParams.SIGNATURE,signature);

            String body = HttpRequest.get(USER_PAGE_URL)
                    .form(JSON.parseObject(JSON.toJSONString(params), new TypeReference<Map<String, Object>>(){}))
                    .header(Headers.FORWARD_HOST, "127.0.0.1")
                    .execute()
                    .body();
            if (StrUtil.isBlank(body)) {
                return R.error("响应为空");
            }
            return JSON.parseObject(body, R.class);
        }catch (Exception e) {
            log.error("userPageByApi error: ", e);
            return R.error(e.getMessage());
        }
    }
}
