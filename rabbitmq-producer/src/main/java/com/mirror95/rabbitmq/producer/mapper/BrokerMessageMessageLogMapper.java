/**
 * Copyright (C), 2019-2022, 海安秩明网络科技工作室
 * FileName: BrokerMessageMessageLogMapper
 * Author:   HYJ
 * Date:     2019/3/10 23:52
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.mirror95.rabbitmq.producer.mapper;

import com.mirror95.rabbitmq.producer.entity.BrokerMessageLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author: mirror_huang
 * @date: 2019/3/10 23:52
 * @qq: 1755496180
 * @description: 消息记录接口
 */
@Repository
public interface BrokerMessageMessageLogMapper {

    /**
     * 查询消息状态为0（发送中）且已经超时的消息集合
     *
     * @return
     */
    List<BrokerMessageLog> query4StatusAndTimeoutMessage();

    /**
     * 重新发送统计count发送次数 +1
     *
     * @param messageId
     * @param updateTime
     */
    void update4ReSend(@Param("messageId") String messageId, @Param(value = "updateTime") Date updateTime);

    /**
     * 更新最新消息发送 成功 or 失败
     *
     * @param messageId
     * @param status
     * @param updateTime
     */
    void changeBrokerMessageLogStatus(@Param("messageId") String messageId, @Param("status") String status, @Param("updateTime") Date updateTime);

    int insertSelective(BrokerMessageLog record);


}
