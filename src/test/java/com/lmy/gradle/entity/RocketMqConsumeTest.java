package com.lmy.gradle.entity;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @description：消耗单MQ
 * @author： LMinY
 * @since： 2021-07-09
 */
@Component
@RocketMQMessageListener(
        topic = "topic_ordinaryMsg",
        consumerGroup = "topic_ordinaryMsg",
        selectorExpression = "tag1||tag2",
        consumeMode = ConsumeMode.ORDERLY
)
public class RocketMqConsumeTest implements RocketMQListener<MessageExt> {


    @Override
    public void onMessage(MessageExt msg) {
        String messageStr = new String(msg.getBody());
        if (msg.getTags().equals("tag1")) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(messageStr + " : " + System.currentTimeMillis());
    }
}
