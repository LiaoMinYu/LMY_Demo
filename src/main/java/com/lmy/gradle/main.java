package com.lmy.gradle;

import com.lmy.gradle.entity.Friend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LMinY
 * @program: demo
 * @description:
 * @date 2020/9/21
 */
public class main extends Thread {

    public static void main(String[] args) {
        List<Friend> friendList = getFriendList();
//        //
//        List<String> nameList = friendList.stream().map(Friend::getName).distinct().collect(Collectors.toList());
//        nameList.forEach(name -> System.out.println(name));
//        //按条件过滤集合
//        List<Friend> youngPeople  = friendList.stream().filter(friend -> friend.getAge() != null &&
//                friend.getAge() <29 && friend.getHeight() > 170).collect(Collectors.toList());
//        System.out.println(youngPeople);
        HashMap<Integer, Friend> map = new HashMap(6);
        int a = 0;
        for (Friend it : friendList) {

            map.put(a, it);
            a++;
        }
//        BigDecimal a = new BigDecimal("1");
//        BigDecimal c = null;
//        BigDecimal b = a.multiply(c);
        //System.out.println(b);
        System.out.println(map);
    }

    public static List<Friend> getFriendList() {
        List<Friend> friendList = new ArrayList<>();
        friendList.add(new Friend("小周", 28, 175L, "郑州", new BigDecimal("101.5")));
        friendList.add(new Friend("小吴", 28, 170L, "洛阳", new BigDecimal("111.5")));
        friendList.add(new Friend("小郑", 29, 176L, "郑州", new BigDecimal("121.5")));
        friendList.add(new Friend("小王", 29, 180L, "北京", new BigDecimal("130")));
        friendList.add(new Friend("小赵", 27, 178L, "苏州", new BigDecimal("140")));
        friendList.add(new Friend("小赵", null, null, "杭州", new BigDecimal("150")));
        friendList.add(new Friend("小周", 28, 175L, "郑州", new BigDecimal("101.5")));
        friendList.add(new Friend("小吴", 28, 170L, "洛阳", new BigDecimal("111.5")));
        friendList.add(new Friend("小郑", 29, 176L, "郑州", new BigDecimal("121.5")));
        friendList.add(new Friend("小王", 29, 180L, "北京", new BigDecimal("130")));
        friendList.add(new Friend("小赵", 27, 178L, "苏州", new BigDecimal("140")));
        friendList.add(new Friend("小赵", null, null, "杭州", new BigDecimal("150")));
        friendList.add(new Friend("小周", 28, 175L, "郑州", new BigDecimal("101.5")));
        friendList.add(new Friend("小吴", 28, 170L, "洛阳", new BigDecimal("111.5")));
        friendList.add(new Friend("小郑", 29, 176L, "郑州", new BigDecimal("121.5")));
        friendList.add(new Friend("小王", 29, 180L, "北京", new BigDecimal("130")));
        friendList.add(new Friend("小赵", 27, 178L, "苏州", new BigDecimal("140")));
        friendList.add(new Friend("小赵", null, null, "杭州", new BigDecimal("150")));
        friendList.add(new Friend("小周", 28, 175L, "郑州", new BigDecimal("101.5")));
        friendList.add(new Friend("小吴", 28, 170L, "洛阳", new BigDecimal("111.5")));
        friendList.add(new Friend("小郑", 29, 176L, "郑州", new BigDecimal("121.5")));
        friendList.add(new Friend("小王", 29, 180L, "北京", new BigDecimal("130")));
        friendList.add(new Friend("小赵", 27, 178L, "苏州", new BigDecimal("140")));
        friendList.add(new Friend("小赵", null, null, "杭州", new BigDecimal("150")));
        return friendList;
    }
}
