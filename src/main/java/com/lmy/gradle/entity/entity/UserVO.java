package com.lmy.gradle.entity.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description：
 * @author： LMinY
 * @since： 2021-06-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserVO {

    private Long id;

    private String city;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Double rank;

    public static UserVO buildSaleOrderVO(User dto) {
        return UserVO.builder()
                .id(dto.getId())
                .name(null)
                .city(dto.getCity())
                .createTime(dto.getCreateTime())
                .updateTime(dto.getUpdateTime())
                .build();
    }
}
