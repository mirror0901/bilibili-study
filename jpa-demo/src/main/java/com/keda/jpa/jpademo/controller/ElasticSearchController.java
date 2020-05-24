/**
 * Copyright (C), 2019-2030, 海安秩明网络科技工作室
 * FileName: ElasticSearchController
 * Author:   HYJ
 * Date:     2019/3/4 23:22
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.keda.jpa.jpademo.controller;

import com.keda.jpa.jpademo.dao.ElasticDao;
import com.keda.jpa.jpademo.entity.Elastic;
import io.swagger.annotations.Api;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.web.bind.annotation.*;


/**
 * @author: mirror_huang
 * @date: 2019/3/4 23:22
 * @qq: 1755496180
 * @description:
 */
@Api(tags = "es")
@RestController
@RequestMapping(value = "/es")
public class ElasticSearchController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private Client client;
    @Autowired
    private ElasticDao elasticDao;

    @PostMapping("/add/{id}")
    public String add(@PathVariable("id") String id) {
        Elastic elastic = new Elastic();
        elastic.setId("1");
        elastic.setName("H.Y.J");
        elastic.setContent("o.o.o");
        elastic.setPrice("26");
        elasticDao.save(elastic);
        return "success";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        Elastic elastic = new Elastic();
        elastic.setId(id);
        elasticDao.delete(elastic);
        return "success";
    }

    @PutMapping("/update")
    public String update(@RequestParam String id, @RequestParam String name) {
        Elastic elastic = new Elastic();
        elastic.setId(id);
        elastic.setName(name);
        elasticDao.save(elastic);
        return "success";
    }

    @GetMapping("/query/{id}")
    public Elastic query(@PathVariable("id") String id) {
        Elastic elastic = elasticDao.queryElasticById(id);
        System.out.println(elastic.toString());
        return elastic;
    }

    @GetMapping("/complexQuery")
    public SearchResponse complexQuery(@RequestBody Elastic elastic) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (elastic.getId() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("id", elastic.getId()));
        }
        if (elastic.getName() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("name", elastic.getName()));
        }
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price").gte(elastic.getPrice());
        boolQueryBuilder.filter(rangeQueryBuilder);
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("test-boot").setTypes("goods");
        searchRequestBuilder.setFrom(0).setSize(10).addSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        return searchRequestBuilder.get();
    }

    @GetMapping(value = "templateQuery")
    public Page<Elastic> templateQuery() {
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("id").is(1)));

        Page<Elastic> elasticPage = elasticsearchTemplate.queryForPage(criteriaQuery, Elastic.class);
        return elasticPage;
    }

}
