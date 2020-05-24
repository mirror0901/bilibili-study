/**
 * Copyright (C), 2019-2022, 海安秩明网络科技工作室
 * FileName: DruidDataSourceConfig
 * Author:   HYJ
 * Date:     2019/3/10 22:18
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.mirror95.rabbitmq.producer.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author: mirror_huang
 * @date: 2019/3/10 22:18
 * @qq: 1755496180
 * @description:
 */
@Slf4j
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig {

    /**
     * 注入数据源配置信息
     */
    @Autowired
    private DruidDataSourceSettings druidDataSourceSettings;

    public static String DRIVER_CLASSNAME;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 创建DataSource
     *
     * @return
     * @throws SQLException
     */
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        DRIVER_CLASSNAME = druidDataSourceSettings.getDriverClassName();
        druidDataSource.setUrl(druidDataSourceSettings.getUrl());
        druidDataSource.setUsername(druidDataSourceSettings.getUsername());
        druidDataSource.setPassword(druidDataSourceSettings.getPassword());
        druidDataSource.setInitialSize(druidDataSourceSettings.getInitialSize());
        druidDataSource.setMinIdle(druidDataSourceSettings.getMinIdle());
        druidDataSource.setMaxActive(druidDataSourceSettings.getMaxActive());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceSettings.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidDataSourceSettings.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidDataSourceSettings.getValidationQuery());
        druidDataSource.setTestWhileIdle(druidDataSourceSettings.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(druidDataSourceSettings.isTestOnBorrow());
        druidDataSource.setTestOnReturn(druidDataSourceSettings.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(druidDataSourceSettings.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceSettings.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setFilters(druidDataSourceSettings.getFilters());
        druidDataSource.setConnectionProperties(druidDataSourceSettings.getConnectionProperties());
        log.info("\n druid datasource config : {} ", druidDataSource);
        return druidDataSource;
    }

    /**
     * 开启事物
     *
     * @return
     * @throws Exception
     */
    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

}
