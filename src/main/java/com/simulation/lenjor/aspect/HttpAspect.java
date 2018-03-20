package com.simulation.lenjor.aspect;




import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HttpAspect {
	
	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
	//声明切入点，可以减少代码冗余度
		@Around("execution(public * com.simulation.lenjor.controller.IndexController.*(..))")
		public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
			logger.info("接收http之前");
			Long startTime = System.currentTimeMillis();
			logger.info("开始时间 :"+startTime);
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = (HttpServletRequest) attributes.getRequest();
			//url
			logger.info("url={}",request.getRequestURL());
			//method
			logger.info("method={}",request.getMethod());
			//ip
			logger.info("ip={}",request.getRemoteAddr());
			//类方法
			logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."
					+joinPoint.getSignature().getName());
			//参数
			Object[]  args = joinPoint.getArgs();
	         for(Object arg:args){
	        	 logger.info("args={}",arg);
	         }
			
			Object object = joinPoint.proceed();  //代理返回值
			logger.info("处理完http之后");
			Long endTime = System.currentTimeMillis();
			logger.info("结束时间 :"+endTime);
			Long costTime = endTime-startTime;
			logger.info("耗时："+ costTime + "ms");
			return object;
		}

}
