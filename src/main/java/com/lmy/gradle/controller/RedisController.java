package com.lmy.gradle.controller;

import com.lmy.gradle.service.RedisService;
import com.lmy.gradle.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LMinY
 * @program: demo
 * @description: redis测试
 * @date 2020/9/25
 */
@RestController
@RequestMapping(value="redis")
public class RedisController {



    @Autowired
    RedisService redisService;

    @RequestMapping(value = "add")
    public String add(@RequestParam String type){
        redisService.addStringRedis();
        return "addStringRedis";

    }

    @RequestMapping(value = "get")
    public Object get(){

        return redisService.getStringRedis();

    }
}
