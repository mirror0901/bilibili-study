package com.mirror95.redislock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-01-15 22:06
 **/
@Slf4j
@RestController
public class IndexController {

    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/deduct_stock")
    public String deductStock() throws InterruptedException {
        String lockKey = "product_001";
        //String clientId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        RLock lock = redisson.getLock(lockKey);
        try {
            lock.lock(30, TimeUnit.SECONDS);
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock + "");
                log.info("\n扣减成功，剩余库存为:" + realStock + "");
            } else {
                log.info("\n扣减失败,库存不足");
            }
        } finally {
            lock.unlock();
        }
        return "操作成功";
    }
}
