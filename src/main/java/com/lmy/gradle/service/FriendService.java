package com.lmy.gradle.service;

import com.lmy.gradle.entity.Friend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LMinY
 * @program: demo
 * @description:
 * @date 2020/9/21
 */
public class FriendService {

    public List<Friend> getFriendList() {
        List<Friend> friendList = new ArrayList<>();
        friendList.add(new Friend("小周", 28, 175L, "郑州", new BigDecimal("101.5")));
        friendList.add(new Friend("小吴", 28, 170L, "洛阳", new BigDecimal("111.5")));
        friendList.add(new Friend("小郑", 29, 176L, "郑州", new BigDecimal("121.5")));
        friendList.add(new Friend("小王", 29, 180L, "北京", new BigDecimal("130")));
        friendList.add(new Friend("小赵", 27, 178L, "苏州", new BigDecimal("140")));
        friendList.add(new Friend("小钱", null, null, "杭州", new BigDecimal("150")));

        return friendList;
    }

}
