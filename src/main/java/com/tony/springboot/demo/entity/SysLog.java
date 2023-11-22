package com.tony.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 系统日志
 * @TableName sys_log
 */
@TableName(value ="sys_log")
@Data
public class SysLog implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 访问用户
     */
    private Long accessUser;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private Object params;

    /**
     * 执行时长(毫秒)
     */
    private Long executeTime;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    private Long createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}