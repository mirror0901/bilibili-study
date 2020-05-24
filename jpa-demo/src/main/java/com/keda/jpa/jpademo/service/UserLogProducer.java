/**
 * Copyright (C), 2019-2030, 海安秩明网络科技工作室
 * FileName: UserLogProducer
 * Author:   HYJ
 * Date:     2019/3/6 21:57
 * Description: 定义消息的发送者
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.keda.jpa.jpademo.service;

import com.alibaba.fastjson.JSON;
import com.keda.jpa.jpademo.entity.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: mirror_huang
 * @date: 2019/3/6 21:57
 * @qq: 1755496180
 * @description:
 */
@Slf4j
@Component
public class UserLogProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送数据
     *
     * @param userId
     */
    public void sendLog(String userId) {
        UserLog userLog = new UserLog();
        userLog.setUserName("userName").setUserId("userId").setState("0");
        log.info("发送用户日志数据:{}", userLog);
        kafkaTemplate.send("test", JSON.toJSONString(userLog));
    }

}
