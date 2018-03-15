package com.simulation.my.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class RequestLogAspect {

    @Pointcut("execution(public * com.simulation.my.controller.*.*(..))")
    public void requestLog() {

    }

    @Around(value = "requestLog()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> requestNames = request.getParameterNames();
        System.out.println("请求参数如下：(key:value)");
        while (requestNames.hasMoreElements()) {
            String key = requestNames.nextElement();
            String value = request.getParameter(key);
            System.out.println(key + " : " + value);
        }
        Long beginTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - beginTime) + "ms");
        return result;
    }

}
