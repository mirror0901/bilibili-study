package com.mirror95.bilibili;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-01-16 00:14
 **/
@RestController
public class IndexControll {

    @GetMapping
    public String hello() {
        return "hello world";
    }

}
