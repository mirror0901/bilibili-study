/**
 * Copyright (C), 2019-2030, 海安秩明网络科技工作室
 * FileName: UserLogConsumer
 * author:   HYJ
 * Date:     2019/3/7 0:50
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.keda.jpa.jpademo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author: mirror_huang
 * @date: 2019/3/7 00:50
 * @qq: 1755496180
 * @description:
 */
@Component
@Slf4j
public class UserLogConsumer {

    /**
     * 监听 topic为 test的消息
     *
     * @param consumerRecord
     */
    @KafkaListener(topics = {"test"})
    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        log.info("===> record = " + kafkaMessage);
        if (kafkaMessage.isPresent()) {
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            System.out.println("消费消息:" + message);
        }
    }

}
