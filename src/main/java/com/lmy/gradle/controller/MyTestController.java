package com.lmy.gradle.controller;

import com.lmy.gradle.service.FriendService;
import org.springframework.web.bind.annotation.RestController;

//反射测试
//@RestController
public class MyTestController {

    public FriendService friendService;

    public FriendService getFriendService() {
        return friendService;
    }

    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }
}
