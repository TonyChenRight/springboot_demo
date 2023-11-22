package com.tony.springboot.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.springboot.demo.entity.SysResource;
import com.tony.springboot.demo.service.SysResourceService;
import com.tony.springboot.demo.mapper.SysResourceMapper;
import org.springframework.stereotype.Service;

/**
* @author tonychen
* @description 针对表【sys_resource(资源名称)】的数据库操作Service实现
* @createDate 2023-11-19 17:43:23
*/
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource>
    implements SysResourceService{

}




