package com.lmy.gradle.entity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LMinY
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends User2{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String city;

    private String name;

    private Date createTime;

    private Date updateTime;
    private BigDecimal rank;

//    @Override
//    public int compareTo(User o) {
//
//        return Integer.compare(Integer.parseInt(city.substring(0,city.length()-1) ),Integer.parseInt(o.city.substring(0,o.city.length()-1) ));
//    }
}
