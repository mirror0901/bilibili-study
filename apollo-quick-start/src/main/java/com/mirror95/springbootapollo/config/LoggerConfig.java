package com.mirror95.springbootapollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description: 把日志的配置部署到Apollo配置中心，但在配置中心修改日志等级，依然需要重启应用才生效
 * @create: 2020-04-24 01:23
 **/
@Configuration
public class LoggerConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggerConfig.class);
    private static final String LOGGER_TAG = "logging.level.";

    @Autowired
    private LoggingSystem loggingSystem;

    @ApolloConfig
    private Config config;

    @ApolloConfigChangeListener
    private void configChangeListter(ConfigChangeEvent changeEvent) {
        refreshLoggingLevels();
    }

    @PostConstruct
    private void refreshLoggingLevels() {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
            if (StringUtils.containsIgnoreCase(key, LOGGER_TAG)) {
                String strLevel = config.getProperty(key, "info");
                LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
                loggingSystem.setLogLevel(key.replace(LOGGER_TAG, ""), level);
                logger.info("{}:{}", key, strLevel);
            }
        }
    }


}
