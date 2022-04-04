package com.lmy.gradle.aop;

import com.lmy.gradle.config.LoginRequired;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.lang.reflect.Method;

/**
 * @author LMinY
 * @program: demo
 * @description:
 * @date 2020/9/28
 */
@Component
@Aspect
@Order(1)
public class LoginProcessInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginProcessInterceptor.class);


    @Pointcut("execution(public * com.lmy.gradle.controller.*.*(..))")
    public void loginUser() {
    }

    @Around("loginUser()")
    public void checkUser(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Method method = methodSignature.getMethod();

        boolean needLogin = !StringUtils.isEmpty(method.getAnnotation(LoginRequired.class));
    }
}