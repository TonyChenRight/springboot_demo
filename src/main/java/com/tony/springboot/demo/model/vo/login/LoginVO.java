package com.tony.springboot.demo.model.vo.login;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginVO {
    @ApiParam(value = "用户名称", example = "user", required = true)
    @NotBlank
    private String username;
    @ApiParam(value = "密码", example = "123456", required = true)
    @NotBlank
    private String password;
}
