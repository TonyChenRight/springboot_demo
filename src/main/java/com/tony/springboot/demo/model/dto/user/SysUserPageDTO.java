package com.tony.springboot.demo.model.dto.user;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tony.springboot.demo.entity.SysUser;
import lombok.Data;

import java.util.Map;

@Data
public class SysUserPageDTO {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态  0:禁用  1:正常
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long createUser;

    private String createUserName;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新人
     */
    private Long updateUser;

    private String updateUserName;

    /**
     * 创建时间
     */
    private Long updateTime;

    public SysUserPageDTO(SysUser entity, Map<Long, String> userNameMap) {
        BeanUtil.copyProperties(entity, this);
        this.createUserName = userNameMap.get(createUser);
        this.updateUserName = userNameMap.get(updateUser);
    }
}
