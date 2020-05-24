/**
 * Copyright (C), 2019-2030, 海安秩明网络科技工作室
 * FileName: ElasticDao
 * Author:   HYJ
 * Date:     2019/3/4 23:19
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.keda.jpa.jpademo.dao;

import com.keda.jpa.jpademo.entity.Elastic;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author: mirror_huang
 * @date: 2019/3/4 23:19
 * @qq: 1755496180
 * @description:
 */
@Component
public interface ElasticDao extends ElasticsearchRepository<Elastic, String> {


    Elastic queryElasticById(String id);



}
