package com.lmy.gradle.entity.mapper;

import com.lmy.gradle.entity.entity.User2;
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
public interface User2Mapper extends BaseMapper<User2> {

    void inserts(List<User2> list2);

    User2 selectInsetTest(@Param("insetTest") String insetTest);
}
