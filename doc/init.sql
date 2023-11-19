use springboot_demo;

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
    `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
    `email` varchar(256) NOT NULL DEFAULT '' COMMENT '邮箱',
    `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态  0:禁用  1:正常',
    `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
    `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
    `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(64) NOT NULL DEFAULT '' COMMENT '角色名',
    `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态  0:禁用  1:正常',
    `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
    `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
    `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色名称';

DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(64) NOT NULL DEFAULT '' COMMENT '资源名称',
    `parent_id` bigint(20) unsigned NOT NULL COMMENT '父级ID',
    `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '类型 1-菜单 2-接口',
    `url` varchar(256) NOT NULL DEFAULT '' COMMENT 'url',
    `weight` tinyint(11) NOT NULL DEFAULT '0' COMMENT '权重',
    `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态  0:禁用  1:正常',
    `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
    `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
    `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源名称';


DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
     `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
     `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
     `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
     `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
     `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
     PRIMARY KEY (`id`),
     UNIQUE KEY `uniq_role_user` (`role_id`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色用户表';


DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
     `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
     `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
     `resource_id` bigint(20) unsigned NOT NULL COMMENT '资源ID',
     `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
     `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
     PRIMARY KEY (`id`),
     UNIQUE KEY `uniq_role_resource` (`role_id`, `resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色资源表';
