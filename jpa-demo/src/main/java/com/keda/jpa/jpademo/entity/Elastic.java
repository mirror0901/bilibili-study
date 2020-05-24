/**
 * Copyright (C), 2019-2030, 海安秩明网络科技工作室
 * FileName: Elastic
 * author:   HYJ
 * Date:     2019/3/4 23:13
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.keda.jpa.jpademo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author: mirror_huang
 * @date: 2019/3/4 23:13
 * @qq: 1755496180
 * @description:
 */

/**
 * indexName 索引名称 可以理解为数据库名 必须小写 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
 * type类型 可以理解为表名
 */
@Getter
@Setter
@ToString
@Document(indexName = "test-boot", type = "goods", shards = 2, replicas = 1, refreshInterval = "-1")
public class Elastic {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String content;

    @Field
    private String price;

}
