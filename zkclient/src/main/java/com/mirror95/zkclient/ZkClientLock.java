package com.mirror95.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description: zkClient实现分布式锁
 * @create: 2020-02-01 02:04
 **/
public class ZkClientLock {

    @Test
    public void lock() throws Exception {
        ZkClient zkClient = new ZkClient("192.168.95.22:2181");
        boolean exists = zkClient.exists("/myLocal");
        //如果节点不存在
        if (!exists) {
            try {
                //临时节点
                zkClient.create("/mylock", "测试分布式锁_zkClient", CreateMode.EPHEMERAL);
                System.out.println("获得锁……");
                System.out.println("处理业务逻辑……");
            } catch (Exception e) {
                System.out.println("获取锁失败……");
            } finally {
                zkClient.close();
            }
        } else {
            System.out.println("获取锁失败……");
            System.out.println("等待锁释放……");
        }
    }

}
