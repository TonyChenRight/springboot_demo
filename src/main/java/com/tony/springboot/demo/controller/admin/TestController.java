package com.tony.springboot.demo.controller.admin;

import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.model.vo.user.SysUserPageVO;
import com.tony.springboot.demo.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "测试接口")
@RestController
@RequestMapping("/admin/test")
public class TestController {

    @Resource
    private TestService testService;

    @ApiOperation("用户分页")
    @GetMapping("/user_page_by_api")
    public R userPageByApi(@Validated SysUserPageVO queryVO) {
        return testService.userPageByApi(queryVO);
    }

}
