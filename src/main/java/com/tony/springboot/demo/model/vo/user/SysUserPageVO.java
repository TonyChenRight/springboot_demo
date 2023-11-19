package com.tony.springboot.demo.model.vo.user;

import cn.hutool.core.util.StrUtil;
import com.tony.springboot.demo.model.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserPageVO extends PageVO {
    @ApiModelProperty(value = "用户名", example = "tony")
    private String username;

    public String getUsername() {
        return StrUtil.isBlank(username) ? null : username;
    }
}
