package com.lmy.gradle.entity.mapper;

import com.lmy.gradle.entity.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LMinY
 * @since 2021-04-20
 */
public interface UserMapper extends BaseMapper<User> {

    void inserts(@Param("list") List<User> list);
}
