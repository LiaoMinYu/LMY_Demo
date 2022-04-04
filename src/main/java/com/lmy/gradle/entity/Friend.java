package com.lmy.gradle.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Friend {
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 身高
     */
    private Long height;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 体重
     */
    private BigDecimal weight;

    public Friend(String name, Integer age, Long height, String city, BigDecimal weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.city = city;
        this.weight = weight;
    }
}
