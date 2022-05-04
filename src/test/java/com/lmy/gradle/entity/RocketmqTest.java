package com.lmy.gradle.entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author LMinY
 * @description rocketmq测试
 * @since 2022-05-04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RocketmqTest {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @Test
    public void ordinaryMsg() {
        for (int i = 1; i < 100; i++) {
            String createMsg = "创建" + i, payMsg = "支付" + i;
            String create = "topic_ordinaryMsg" + ":" + "tag1",
                    pay = "topic_ordinaryMsg" + ":" + "tag2";
            Message<String> createMessage = MessageBuilder.withPayload(createMsg)
                    .setHeader(RocketMQHeaders.KEYS, "orderId1").build();

            Message<String> payMessage = MessageBuilder.withPayload(payMsg)
                    .setHeader(RocketMQHeaders.KEYS, "orderId1").build();
            //有序发送
            rocketMQTemplate.syncSendOrderly(create, createMessage, String.valueOf(i));
            rocketMQTemplate.syncSendOrderly(pay, payMessage, String.valueOf(i));
        }

    }

}
