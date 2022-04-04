package com.lmy.gradle.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.lmy.gradle.entity.entity.User;
import com.lmy.gradle.entity.entity.User2;
import com.lmy.gradle.entity.mapper.User2Mapper;
import com.lmy.gradle.entity.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LMinY
 * @date 2021-04-20
 */
@Service
public class MysqlService {

    @Resource
    User2Mapper user2Mapper;

    @Resource
    UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public Long savaManyTalbe() {
        List<User> list = new ArrayList<>();
        long tim = System.currentTimeMillis();
        for (int i = 10001; i <= 20000; i++) {
            User user = new User();
            user.setName("测试表"+i);
            user.setCity("广州"+i);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            list.add(user);
        }
        userMapper.inserts(list);
        return System.currentTimeMillis() - tim;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long savaManyTalbe2() {
        long tim = System.currentTimeMillis();
        for (int i = 0; i <= 10000; i++) {
            User2 user = new User2();
            user.setName("测试表1");
            user.setCity("广州");
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user2Mapper.insert(user);
        }
        return System.currentTimeMillis() - tim;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insetTest() {

        User2 user = new User2();
        user.setName("insetTest");
        user.setCity("广州");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user2Mapper.insert(user);
        insetTest2();
    }

    public void insetTest2(){
        User2 u =  user2Mapper.selectInsetTest("insetTest");
        System.out.println(u);
    }


}
