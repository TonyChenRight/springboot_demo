package com.tony.springboot.demo.controller;

import com.tony.springboot.demo.model.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "主页")
@RestController
public class IndexController {

    @GetMapping({"/info"})
    public R index() {
        return R.ok();
    }

}