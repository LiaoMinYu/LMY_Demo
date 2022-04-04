package com.lmy.gradle.service;

import com.lmy.gradle.constant.RedisKeyConst;
import com.lmy.gradle.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author LMinY
 * @program: demo
 * @description: redis
 * @date 2020/9/25
 */

@Service
public class RedisService {

    @Resource
    RedisUtil redisUtil;
    @Resource
    private RedisTemplate redisTemplate;
    public Boolean addStringRedis(){
        Map<String,String> map = new HashMap<>();
        map.put("A","aa");
        map.put("b","bb");
        map.put("c","cc");
        map.put("d","dd");
        redisUtil.set(RedisKeyConst.POSTER_KEY+1, map);
        redisUtil.get(RedisKeyConst.POSTER_KEY+1);
        map.put("A","66666");
        redisUtil.set(RedisKeyConst.POSTER_KEY+1, map);
        redisUtil.get(RedisKeyConst.POSTER_KEY+1);



        SetOperations<String, Map<String,String>> set = redisTemplate.opsForSet();
        map.put("A","aa");
        map.put("b","bb");
        map.put("c","cc");
        map.put("d","dd");
//        set.add("set1","22");
//        set.add("set1","33");
//        set.add("set1","44");
        set.add("111",map);
        Set<String> resultSet =redisTemplate.opsForSet().members("setTest");
        System.out.println("resultSet:"+resultSet);

        List<String> list = new ArrayList<>();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");
        redisUtil.setList(RedisKeyConst.POSTER_KEY+"list",list);
        return redisUtil.set(RedisKeyConst.POSTER_KEY+2, map);
    }

    public Object getStringRedis() {
        return redisTemplate.keys(RedisKeyConst.POSTER_KEY+"*");
    }
}
