package com.tony.springboot.demo.controller.common;

import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.model.bo.UserBO;
import com.tony.springboot.demo.model.vo.login.LoginVO;
import com.tony.springboot.demo.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "登录相关")
@RequestMapping("/common")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@Validated @RequestBody LoginVO loginVO) {
        return loginService.login(loginVO);
    }

    @ApiOperation("获取当前用户")
    @GetMapping("/current_user")
    public R getCurrentUser(HttpServletRequest request) {
        UserBO currentUser = loginService.getCurrentUser(request);
        return R.ok(currentUser);
    }
}
