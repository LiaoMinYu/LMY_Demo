package com.lmy.gradle.utils;

import com.lmy.gradle.constant.RedisKeyConst;
import com.lmy.gradle.service.RedisService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LMinY
 * @program: demo
 * @description:
 * @date 2020/9/19
 */
public class Utils {

    public static String getUrl(HttpServletRequest req){
        String url = req.getLocalAddr() + ":" + req.getLocalPort()+"/";
        req.getLocalAddr();
        return url;
    }
    public String test(HttpServletRequest req){

        return null;
    }

}
