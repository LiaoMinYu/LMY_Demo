package com.lmy.gradle.entity.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class User2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String city;

    private String name;

    private Date createTime;

    private Date updateTime;
}
