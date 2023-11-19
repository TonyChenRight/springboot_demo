package com.tony.springboot.demo.model;

import com.tony.springboot.demo.constant.Codes;
import lombok.Data;

@Data
public class R {
    private int code;
    private String message;
    private Object data;

    public static R ok() {
        R result=new R();
        result.code = Codes.SUCCESS;
        return result;
    }
    public static R ok(Object data) {
        R result=new R();
        result.code = Codes.SUCCESS;
        result.data = data;
        return result;
    }

    public static R error(String message) {
        R result=new R();
        result.code = Codes.SYSTEM_ERROR;
        result.message = message;
        return result;
    }

    public static R error(int code, String message) {
        R result=new R();
        result.code = code;
        result.message = message;
        return result;
    }

}
