package com.tony.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 资源名称
 * @TableName sys_resource
 */
@TableName(value ="sys_resource")
@Data
public class SysResource implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 类型 1-菜单 2-接口
     */
    private Integer type;

    /**
     * url
     */
    private String url;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 状态  0:禁用  1:正常
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新人
     */
    private Long updateUser;

    /**
     * 创建时间
     */
    private Long updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}