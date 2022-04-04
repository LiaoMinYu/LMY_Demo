package com.lmy.gradle.config;

import java.lang.annotation.*;

/**
 * @author LMinY
 * @program: demo
 * @description:限流注解
 * @date 2020/9/28
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    double limitNum() default 20;  //默认每秒放入桶中的token
}