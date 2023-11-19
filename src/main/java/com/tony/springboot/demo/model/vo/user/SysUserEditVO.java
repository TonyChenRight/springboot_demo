package com.tony.springboot.demo.model.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class SysUserEditVO implements Serializable {
    @ApiModelProperty(value = "用户ID,修改必填", example = "1")
    private Long id;
    @ApiModelProperty(value = "用户名", example = "user", required = true)
    @NotBlank
    private String username;
    @ApiModelProperty(value = "用户名", example = "123456", required = true)
    @NotBlank
    private String password;
    @ApiModelProperty(value = "邮箱", example = "user@tony.com", required = true)
    @NotBlank
    private String email;
}
