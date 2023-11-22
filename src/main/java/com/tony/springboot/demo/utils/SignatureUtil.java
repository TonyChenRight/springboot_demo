package com.tony.springboot.demo.utils;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.tony.springboot.demo.constant.SignatureParams;

import java.util.*;

public class SignatureUtil {

    public static String calcSignature(String method, String host, String path, String secretKey, Map<String, String> params) {

        StringBuilder sb =new StringBuilder();
        sb.append(method.toUpperCase()).append("\n").append(host.toLowerCase()).append("\n").append(path).append("\n");
        sb.append(secretKey).append("\n");

        TreeMap<String, String> sortedMap = new TreeMap<>(params);
        sortedMap.remove(SignatureParams.SIGNATURE);
        sortedMap.remove(SignatureParams.TIMESTAMP);
        sortedMap.remove(SignatureParams.KEY_ID);

        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                sb.append(key).append("=").append(URLEncodeUtil.encodeQuery(value)).append("&");
            }
        }
        sb.setLength(sb.length() - 1);
        return DigestUtil.md5Hex(sb.toString());
    }
}
