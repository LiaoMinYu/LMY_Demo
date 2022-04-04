package com.lmy.gradle.entity;

import com.lmy.gradle.HashMapDemo;
import com.lmy.gradle.elsa.Elsa;
import com.lmy.gradle.entity.entity.User;
import com.lmy.gradle.entity.entity.User2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Resource
    private Elsa elsa;
    @Resource
    HashMapDemo hashMap;

    @Test
    void contextLoads() {

    }
//
//    @Resource
//    HashMapDemo hashDemo;


    @Test
    public void getIdTest() {
        long c = Stream.generate(() -> 1)
                .map(i -> elsa.getUid())
                .limit(10)
                .peek(System.out::println)
                .count();
        Assertions.assertEquals(c, 10);
    }

    @Test
    public void test() {

        List<User> users = hashMap.getSaleHashMap1().get("44");
        List<User2> s = hashMap.getSaleHashMap2().get("测试表10012");
        System.out.println(users);
        System.out.println(s);


    }


    public static void main(String[] args) throws ParseException {
        List<User> users = new ArrayList<>();
        User a = new User();
        User b = new User();
        User c = new User();
        User d = new User();
        d.setRank(BigDecimal.valueOf(122));
        a.setRank(BigDecimal.valueOf(0.04));
        b.setRank(BigDecimal.valueOf(33));
        c.setRank(BigDecimal.valueOf(11));

        users.add(a);
        users.add(b);
        users.add(c);
        users.add(d);
        //users.sort(Comparator.comparing(User::getCity));
//       　Collections.sort(users, new Comparator<User>() {
//        　　　　@Override
//        　　　　public int compare(User u1, User u2) {
//    　　　　　　int diff = Integer.parseInt(u1.getCity().substring(0,u1.getCity().length()-1) )- Integer.parseInt(u2.getCity().substring(0,u2.getCity().length() - 1));
//        　　　　　　if (diff > 0) {
//        　　　　　　　　return 1;
//        　　　　　　}else if (diff < 0) {
//        　　　　　　　　return -1;
//        　　　　　　}
//        　　　　　　return 0; //相等为0
//        　　　　}
//        　　}); // 按年龄排序
//
//        Collections.sort(users, new Comparator<User>() {
//
//                    @Override
//                    public int compare(User u1, User u2) {
//                        //  int i = o1.getScore() - o2.getScore();
//                       Integer a = Integer.valueOf(u1.getCity().substring(0, u1.getCity().length() - 1)) * 100;
//                        int i = Integer.parseInt(u1.getCity().substring(0, u1.getCity().length() - 1)) * 1000 - Integer.parseInt(u2.getCity().substring(0, u2.getCity().length() - 1)) * 1000;
//
//                        if (i == 0) {
//                            return Integer.parseInt(u1.getCity().substring(0, u1.getCity().length() - 1))*1000 - Integer.parseInt(u2.getCity().substring(0, u2.getCity().length() - 1))*1000;
//                        }
//                        return i;
//                    }
//                });
//        Collections.reverse(users);
//        System.out.println(users);
        users.sort(Comparator.comparing(User::getRank));
       // Arrays.sort(new List[]{users});
    /*    AtomicInteger num = new AtomicInteger(0);
        IntStream.range(0, 4).forEach(i -> {
            num.set(i);
            System.out.println(i);
        });
        System.out.println(num);*/
//        String initValue = "0.00";
//        String[] split = initValue.toString().split("\\.");
//        System.out.println(split);

        System.out.println(users);
    }
}
