package com.tony.springboot.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.springboot.demo.entity.SysRoleUser;
import com.tony.springboot.demo.service.SysRoleUserService;
import com.tony.springboot.demo.mapper.SysRoleUserMapper;
import org.springframework.stereotype.Service;

/**
* @author tonychen
* @description 针对表【sys_role_user(角色用户表)】的数据库操作Service实现
* @createDate 2023-11-19 17:43:39
*/
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser>
    implements SysRoleUserService{

}




