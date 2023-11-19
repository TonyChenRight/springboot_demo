package com.tony.springboot.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.entity.SysUser;
import com.tony.springboot.demo.model.vo.user.SysUserEditVO;
import com.tony.springboot.demo.model.vo.user.SysUserPageVO;
import com.tony.springboot.demo.model.vo.user.SysUserStatusVO;

import java.util.Set;

/**
* @author tonychen
* @description 针对表【sys_user(系统用户)】的数据库操作Service
* @createDate 2023-11-18 19:13:09
*/
public interface SysUserService extends IService<SysUser> {

    R userPage(SysUserPageVO query);

    R userEdit(SysUserEditVO edit);

    R userStatus(SysUserStatusVO statusVO);

    Set<String> queryUserApis(Long userId);

    SysUser queryByUsername(String username);
}
