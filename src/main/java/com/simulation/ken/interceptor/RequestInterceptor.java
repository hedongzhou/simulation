package com.simulation.ken.interceptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.simulation.ken.config.BeanConfig;

public class RequestInterceptor implements HandlerInterceptor {

	Logger logger = Logger.getLogger(this.getClass().getName());

	private long startTime;

	private long endTime;

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception modelAndView)
			throws Exception {
		if (this.isBanApiLog(request.getRequestURI())) {
			return;
		}
		// 记录结束时间
		this.endTime = System.currentTimeMillis();
		// 打印消耗的时间
		logger.info("consume time : " + (this.endTime - this.startTime) + "ms");

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 指定API不打印日志
		if (this.isBanApiLog(request.getRequestURI())) {
			return true;
		}

		// 设置线程名
		String threadName = request.getRequestURI() + "-"
				+ Thread.currentThread().getId() + "-"
				+ System.currentTimeMillis();
		Thread.currentThread().setName(threadName);

		// 记录开始时间
		this.startTime = System.currentTimeMillis();

		// 打印请求参数
		StringBuffer requestParamInfo = new StringBuffer("request params : ");
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String parameterValue = request.getParameter(parameterName);
			requestParamInfo
					.append(parameterName + "=" + parameterValue + "; ");
		}
		logger.info(requestParamInfo.toString());

		return true;
	}

	/**
	 * 是否为被禁止输出日志的API
	 * 
	 * @param requestUri
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	private boolean isBanApiLog(String requestUri) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				BeanConfig.class);
		ArrayList<String> banApis = ctx.getBean("banApis", ArrayList.class);
		if (banApis.contains(requestUri)) {
			return true;
		}
		return false;
	}

}
