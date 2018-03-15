package com.simulation.my.aspect;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequestLogAspect {

	@Pointcut("execution(public * com.simulation.my.controller.*.*(..))")
	public void requestLog() {

	}

	@Around("requestLog()")
	public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// before
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Enumeration<String> parameters = request.getParameterNames();
		System.out.println("请求参数：");
		while (parameters.hasMoreElements()) {
			String key = parameters.nextElement();
			String value = request.getParameter(key);
			System.out.println(key + ":" + value);
		}
		Long beginTime = System.currentTimeMillis();
		System.out.println("计时开始");
		Object result = proceedingJoinPoint.proceed();
		Long endTime = System.currentTimeMillis();
		System.out.println("计时结束");
		System.out.println("耗时:" + (endTime - beginTime) + "ms");
		return result;
	}

}
