package com.tony.springboot.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.springboot.demo.entity.SysRoleResource;
import com.tony.springboot.demo.service.SysRoleResourceService;
import com.tony.springboot.demo.mapper.SysRoleResourceMapper;
import org.springframework.stereotype.Service;

/**
* @author tonychen
* @description 针对表【sys_role_resource(角色资源表)】的数据库操作Service实现
* @createDate 2023-11-19 17:43:29
*/
@Service
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource>
    implements SysRoleResourceService{

}




