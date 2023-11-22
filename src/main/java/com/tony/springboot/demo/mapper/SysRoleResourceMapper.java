package com.tony.springboot.demo.mapper;

import com.tony.springboot.demo.entity.SysRoleResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

/**
* @author tonychen
* @description 针对表【sys_role_resource(角色资源表)】的数据库操作Mapper
* @createDate 2023-11-19 17:43:29
* @Entity com.tony.springboot.demo.entity.SysRoleResource
*/
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

    Set<String> queryUserApis(List<Long> roleIds);

}




