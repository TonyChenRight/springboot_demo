package com.tony.springboot.demo.controller.admin.system;

import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.model.vo.user.SysUserStatusVO;
import com.tony.springboot.demo.service.SysUserService;
import com.tony.springboot.demo.model.vo.user.SysUserEditVO;
import com.tony.springboot.demo.model.vo.user.SysUserPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Resource
    private SysUserService sysUserService;

    @ApiOperation("用户分页")
    @GetMapping("/page")
    public R userPage(@Validated SysUserPageVO queryVO) {
        return sysUserService.userPage(queryVO);
    }

    @ApiOperation("用户编辑")
    @PostMapping("/edit")
    public R userEdit(@Validated @RequestBody SysUserEditVO editVO) {
        return sysUserService.userEdit(editVO);
    }

    @ApiOperation("用户状态")
    @PostMapping("/status")
    public R userStatus(@Validated @RequestBody SysUserStatusVO statusVO) {
        return sysUserService.userStatus(statusVO);
    }

}
