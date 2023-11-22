package com.tony.springboot.demo.mapper;

import com.tony.springboot.demo.entity.SysRoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author tonychen
* @description 针对表【sys_role_user(角色用户表)】的数据库操作Mapper
* @createDate 2023-11-19 17:43:39
* @Entity com.tony.springboot.demo.entity.SysRoleUser
*/
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

    List<Long> queryRoleIdsByUserId(Long userId);
}




