package com.mirror95.springbootapollo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-04-24 01:12
 **/
@RestController
public class HelloController {

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    private String port;

    @GetMapping("hi")
    public String hi(String name) {
        logger.debug("debug log...");
        logger.info("info log...");
        logger.warn("warn log...");
        return "hi " + name + " ,i am from port:" + port;
    }

}
