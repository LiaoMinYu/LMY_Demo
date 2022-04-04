package com.lmy.gradle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lmy.gradle.entity.entity.User;
import com.lmy.gradle.entity.entity.User2;
import com.lmy.gradle.entity.mapper.User2Mapper;
import com.lmy.gradle.entity.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LMinY
 * @description
 * @since 2021-10-03
 */
@Component
public class HashMapDemo {


    @Resource
    User2Mapper user2Mapper;

    @Resource
    UserMapper userMapper;

    private final Map<String, List<User2>> saleHashMap2 = new HashMap();
    private final Map<String, List<User>> saleHashMap1 = new HashMap();


    public Map<String, List<User2>> getSaleHashMap2() {
        return saleHashMap2;
    }

    public Map<String, List<User>> getSaleHashMap1() {
        return saleHashMap1;
    }


    public void init() {
        QueryWrapper<User2> queryWrapper = new QueryWrapper<>();
        List<User2> user2s = user2Mapper.selectList(queryWrapper);
        Map<String, List<User2>> collect = user2s.stream().collect(Collectors.groupingBy(User2::getName));
        saleHashMap2.putAll(collect);
        QueryWrapper<User> userMappers = new QueryWrapper<>();
        List<User> users = userMapper.selectList(userMappers);
        Map<String, List<User>> collect1 = users.stream().collect(Collectors.groupingBy(User::getName));
        saleHashMap1.putAll(collect1);
        System.out.println("1111111");
    }

}
