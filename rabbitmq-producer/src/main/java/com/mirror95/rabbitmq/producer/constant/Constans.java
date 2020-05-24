/**
 * Copyright (C), 2019-2022, 海安秩明网络科技工作室
 * FileName: Constans
 * Author:   HYJ
 * Date:     2019/3/10 23:44
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.mirror95.rabbitmq.producer.constant;

/**
 * @author: mirror_huang
 * @date: 2019/3/10 23:44
 * @qq: 1755496180
 * @description:
 */
public class Constans {

    /**
     * 发送中
     */
    public static final String ORDER_SENDING = "0";

    /**
     * 发送成功
     */
    public static final String ORDER_SEND_SUCCESS = "1";

    /**
     * 发送失败
     */
    public static final String ORDER_SEND_FAILURE = "2";
    /**
     * 分钟超时单位：min
     */
    public static final int ORDER_TIMEOUT = 1;

}
