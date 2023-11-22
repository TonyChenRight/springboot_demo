package com.tony.springboot.demo.controller.api;

import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.model.vo.user.SysUserPageVO;
import com.tony.springboot.demo.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "用户内部接口")
@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Resource
    private SysUserService sysUserService;

    @ApiOperation("用户分页")
    @GetMapping("/page")
    public R userPage(@Validated SysUserPageVO queryVO) {
        return sysUserService.userPage(queryVO);
    }
}
