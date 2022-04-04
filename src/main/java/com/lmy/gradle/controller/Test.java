package com.lmy.gradle.controller;

import com.lmy.gradle.config.SysLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LMinY
 * @program: demo
 * @description:
 * @date 2020/9/19
 */
@Controller
public class Test {

    @Autowired
 //   private WxMpService wxMpService;

    static Logger log =  LoggerFactory.getLogger(Test.class);
/*    @RequestMapping(value= "home")
    public String test (){
        return "home.html";
    }*/

    @RequestMapping("test")
    public ModelAndView alipayforward(HttpServletRequest req, HttpServletResponse resp) throws Exception {
       // ModelAndView mv = new ModelAndView();
        //mv.addObject("exceptionMsg","ni hao,ni ma");
        //mv.setViewName("/home");
        return new ModelAndView("/home");
    }
    @RequestMapping("/js-sdk")
    public Object getJsSdk() throws Exception {
       return null;
    }

    @SysLog(value = "测试Aop注解",limit = 3)
    @RequestMapping(value = "syslog",method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}
