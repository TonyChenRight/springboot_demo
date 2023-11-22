package com.tony.springboot.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.springboot.demo.entity.SysLog;
import com.tony.springboot.demo.service.SysLogService;
import com.tony.springboot.demo.mapper.SysLogMapper;
import org.springframework.stereotype.Service;

/**
* @author tonychen
* @description 针对表【sys_log(系统日志)】的数据库操作Service实现
* @createDate 2023-11-21 09:59:24
*/
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog>
    implements SysLogService{

}




