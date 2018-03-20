package com.simulation.lenjor.aspect;




import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
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
	private Long startTime = 0L;
	private Long endTime = 0L;
	//声明切入点，可以减少代码冗余度
		@Pointcut("execution(public * com.simulation.lenjor.controller.IndexController.*(..))")
		public void log() {	
		}
		@Before("log()")
		public void doBefore(JoinPoint joinPoint) {
			logger.info("接收http之前");
			startTime = System.currentTimeMillis();
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
			logger.info("args={}",joinPoint.getArgs());
			
		}
		@After("log()")
		public void doAfter() {
			logger.info("处理完http之后");
			endTime = System.currentTimeMillis();
			logger.info("结束时间 :"+endTime);
			double costTime = (endTime-startTime)/60.0;
			logger.info("耗时："+ costTime);
		}

		/**
		 * 获取返回的内容
		 * @param object
		 */
	    @AfterReturning(returning = "object", pointcut = "log()")
	    public void doAfterReturning(Object object) {
	        logger.info("response={}", object.toString());
	    }
}
