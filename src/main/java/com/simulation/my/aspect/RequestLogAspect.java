package com.simulation.my.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class RequestLogAspect {

    private Long beforeTime;

    private Long afterTime;

    @Pointcut("execution(public * com.simulation.my.controller.*.*(..))")
    public void requestLog() {

    }

    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 输出参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> requestNames = request.getParameterNames();
        System.out.println("before:");
        System.out.println("请求参数如下：(key:value)");
        while (requestNames.hasMoreElements()) {
            String key = requestNames.nextElement();
            String value = request.getParameter(key);
            System.out.println(key + " : " + value);
        }
        // 计时开始
        beforeTime = System.currentTimeMillis();
    }

    @After("requestLog()")
    public void doAfter(JoinPoint joinPoint) {
        // 计时结束
        afterTime = System.currentTimeMillis();
        System.out.println("after:");
        System.out.println("耗时: " + (afterTime-beforeTime) + "ms");
    }

    public Long getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(Long beforeTime) {
        this.beforeTime = beforeTime;
    }

    public Long getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(Long afterTime) {
        this.afterTime = afterTime;
    }
}
