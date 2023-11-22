package com.tony.springboot.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.springboot.demo.entity.SysRole;
import com.tony.springboot.demo.service.SysRoleService;
import com.tony.springboot.demo.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author tonychen
* @description 针对表【sys_role(角色名称)】的数据库操作Service实现
* @createDate 2023-11-19 17:04:36
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

}




