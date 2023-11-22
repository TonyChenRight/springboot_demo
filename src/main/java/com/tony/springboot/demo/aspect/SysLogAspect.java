package com.tony.springboot.demo.aspect;

import com.alibaba.fastjson.JSON;
import com.tony.springboot.demo.context.UserConext;
import com.tony.springboot.demo.entity.SysLog;
import com.tony.springboot.demo.model.bo.UserBO;
import com.tony.springboot.demo.service.SysLogService;
import com.tony.springboot.demo.utils.HttpContextUtils;
import com.tony.springboot.demo.utils.IPUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Slf4j
@Aspect
@Component
public class SysLogAspect {
	@Resource
	private SysLogService sysLogService;

	@Pointcut("execution(* com.tony.springboot.demo.controller..*.*(..))")
	public void includePointCut() {
		
	}

	@Pointcut("execution(* com.tony.springboot.demo.controller.IndexController.*(..))")
	public void excludePointCut() {

	}

	@Around("includePointCut() && !excludePointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();

		SysLog sysLog = new SysLog();

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0) {
			try{
				String params = JSON.toJSONString(args[0]);
				sysLog.setParams(params);
			}catch (Exception e){
				log.error("params to json error: ", e);
			}
		}

		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));

		//用户
		UserBO user = UserConext.getUser();
		if(user != null) {
			sysLog.setAccessUser(user.getUserId());
		}

		sysLog.setExecuteTime(time);
		sysLog.setCreateTime(System.currentTimeMillis());
		//保存系统日志
		sysLogService.save(sysLog);
		log.info("save sys log : {}", JSON.toJSONString(sysLog));
	}
}
