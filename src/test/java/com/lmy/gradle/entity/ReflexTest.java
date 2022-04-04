package com.lmy.gradle.entity;


import com.lmy.gradle.controller.MyTestController;
import com.lmy.gradle.service.FriendService;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射测试
 * @author LMY
 * @date 2020/4/4 6:27 下午
*/
public class ReflexTest {
    @Test
    public void test() throws Exception{
        MyTestController myTestController = new MyTestController();
        Class<? extends MyTestController> clazz = myTestController.getClass();
        //创建对象
       FriendService friendService = new FriendService();
        System.out.println(friendService);
        //获取所有属性
        Field friendFile= clazz.getDeclaredField("friendService");
        //设置可以访问私有方法
        friendFile.setAccessible(true);
        //通过方法访问，才能够设置属性值
        //获取方法名
        String name = friendFile.getName();
        //拼接方法名称
        name = name.substring(0,1).toUpperCase() + name.substring(1,name.length());
        String setMothName = "set" + name;

        //通过方法注入属性对象
        Method method = clazz.getMethod(setMothName, FriendService.class);
        //将friendservice注入myTestController
        method.invoke(myTestController,friendService);
        System.out.println(myTestController.friendService);

    }
}
