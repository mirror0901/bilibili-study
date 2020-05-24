/**
 * Copyright (C), 2019-2020, 海安秩明网络科技工作室
 * FileName: Order
 * Author:   HYJ
 * Date:     2019/3/10 16:46
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.mirror95.rabbitmq.producer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: mirror_huang
 * @date: 2019/3/10 16:46
 * @qq: 1755496180
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Order implements Serializable {

    private static final long serialVersionUID = -8619276724100112169L;

    private String id;
    private String name;
    /**
     * 存储消息发送的唯一标识
     */
    private String messageId;

}
