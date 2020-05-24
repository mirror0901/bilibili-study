/**
 * Copyright (C), 2019-2022, 海安秩明网络科技工作室
 * FileName: OrderReceiver
 * Author:   HYJ
 * Date:     2019/3/10 18:06
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.mirror95.rabbitmq.producer.consumer;

import com.mirror95.rabbitmq.producer.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: mirror_huang
 * @date: 2019/3/10 18:06
 * @qq: 1755496180
 * @description:
 */
@Component
public class OrderReceiver {

    /**
     * @param order
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(name = "order-exchange", durable = "true", type = "topic"),
            key = "order.*"
    )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order,
                               @Headers Map<String, Object> headers,
                               Channel channel) throws Exception {
        //消费者操作:
        System.out.println("-------------收到消息,开始消费-----------");
        System.out.println("订单ID:" + order.getId());

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //ACK
        channel.basicAck(deliveryTag, false);
    }

}
