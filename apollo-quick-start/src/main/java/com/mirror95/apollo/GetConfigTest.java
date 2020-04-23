package com.mirror95.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description: 获取apollo的配置
 * @create: 2020-04-22 02:02
 **/
public class GetConfigTest {
    public static void main(String[] args) {
        //需要在vm options 配置 -Dapp.id=yj-safe-server -Denv=DEV -Ddev_meta=http://192.168.95.23:8080 -Dapollo.cluster=jiangsu -Dapollo.cacheDir=/opt/data/apollo-config
        Config appConfig = ConfigService.getAppConfig();//获取默认namespace下的配置信息
        //ConfigService.getConfig("");//读取指定namespace下的配置信息
        //获取配置信息
        String property = appConfig.getProperty("sms.enable", null);
        String propertyName = appConfig.getProperty("name", null);
        String propertyUnExist = appConfig.getProperty("unExist", null);
        System.out.println(property);
        System.out.println(propertyName);
        System.out.println(propertyUnExist);
    }
}
