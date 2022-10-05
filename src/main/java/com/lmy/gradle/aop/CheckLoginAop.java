package com.lmy.gradle.aop;

import com.lmy.gradle.entity.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author LMinY
 * @description 校验登录信息AOP
 * @since 2022-08-31
 */
@Slf4j
@Aspect
@Component
public class CheckLoginAop {

    private User user;

    @Pointcut("@annotation(com.lmy.gradle.aop.CheckLogin)")
    public void pointCut() {
    }

    @Before("pointCut() && @annotation(annotationContent)")
    public void doBefore(JoinPoint joinPoint, CheckLogin annotationContent) {
        if (Arrays.stream(joinPoint.getArgs()).findAny().isPresent()) {
            Arrays.stream(joinPoint.getArgs()).forEach(it -> {
                if (it instanceof User) {
                    this.user = (User) it;
                    System.out.println("未登录或登录已过期，请重新登录");
                    if (annotationContent.checkDeptId()) {
                        System.out.println("未登录或登录已过期，无法获取当前登录人部门信息，请联系管理员");

                    }
                    if (annotationContent.checkTenantId()) {
                        System.out.println("未登录或登录已过期，无法获取当前登录人租户信息，请联系管理员");
                    }
                }
            });
        }
    }

}
