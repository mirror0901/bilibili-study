package com.mirror95.rabbitmq.producer.producer;

import com.mirror95.rabbitmq.producer.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author: mirror_huang
 * @date: 2019/3/10 17:43
 * @qq: 1755496180
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderSenderTest {

    @Autowired
    private OrderSender orderSender;

    @Test
    public void sendOrder() throws Exception {
        Order order = new Order();
        order.setId("D" + System.currentTimeMillis());
        order.setName("测试订单一");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
        orderSender.sendOrder(order);
    }

}