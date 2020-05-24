/**
 * Copyright (C), 2018-2019, 海安秩明网络科技工作室
 * FileName: OrderMapper
 * Author:   HYJ
 * Date:     2019/3/10 23:53
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.mirror95.rabbitmq.producer.mapper;

import com.mirror95.rabbitmq.producer.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * @author: mirror_huang
 * @date: 2019/3/10 23:53
 * @qq: 1755496180
 * @description:订单接口
 */
@Repository
public interface OrderMapper {

    int insert(Order record);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


}
