package com.lmy.gradle.aop;

import java.lang.annotation.*;

/**
 * @author LMinY
 * @description 校验登录信息
 * @since 2022-03-15
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLogin {

    /**
     * 校验租户
     */
    boolean checkTenantId() default true;

    /**
     * 校验部门
     */
    boolean checkDeptId() default true;
}
