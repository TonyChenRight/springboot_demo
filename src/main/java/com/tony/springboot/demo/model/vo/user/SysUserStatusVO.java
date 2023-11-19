package com.tony.springboot.demo.model.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SysUserStatusVO implements Serializable {
    @ApiModelProperty(value = "用户ID,修改必填", example = "1")
    private Long id;
    @ApiModelProperty(value = "状态 0-禁用 1-启用", example = "1", required = true)
    @NotNull
    @Range(min = 0, max = 1)
    private Integer status;
}
