package com.lmy.gradle.controller;

import com.lmy.gradle.HashMapDemo;
import com.lmy.gradle.entity.entity.User;
import com.lmy.gradle.entity.entity.User2;
import com.lmy.gradle.entity.entity.UserVO;
import com.lmy.gradle.entity.mapper.UserMapper;
import com.lmy.gradle.service.MysqlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据库测试
 * @author LMinY
 * @date 2021-04-20
 */
@RestController
@RequestMapping(value="mysql")
public class MysqlController {
    @Resource
    MysqlService mysqlService;
    @Resource
    UserMapper userMapper;
    @Resource
    HashMapDemo hashMap;

    @GetMapping("saveManyTable")
    public Mono<Long> saveManyTable(@RequestParam(value = "type") String type){
        if(type.equals("2")){
            return Mono.just(mysqlService.savaManyTalbe());
        }
        return Mono.just(mysqlService.savaManyTalbe2());
    }


    @GetMapping("detail")
    public Mono<UserVO> detail(@RequestParam(value = "id") Long id){
        User user = userMapper.selectById(id);

        return Mono.just(UserVO.buildSaleOrderVO(user));
    }


    @GetMapping("test")
    public void test(){
        hashMap.init();
        List<User> users = hashMap.getSaleHashMap1().get("44");
        List<User2> s = hashMap.getSaleHashMap2().get("测试表10012");
        System.out.println(users);
        System.out.println(s);
    }

    @GetMapping("insetTest")
    public void insetTest(){
        mysqlService.insetTest();
    }
}
