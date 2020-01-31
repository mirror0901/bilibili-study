package com.mirror95.zkclient;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description: curator使用
 * @create: 2020-01-21 00:27
 **/
public class TestZK02 {

    @Test
    public void creatPath() throws Exception {
        //1,创建重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(2000, 3);
        //2,创建CuratorFramework对象
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .retryPolicy(retryPolicy)
                .connectString("192.168.95.22:2181,192.168.95.22:2182,192.168.95.22:2183")
                .sessionTimeoutMs(6000)
                .build();
        //3,开启启动
        curatorFramework.start();
        //4,建立节点:支持多级目录同时创建 指定节点类型 （不加withMode默认为持久类型节点）、路径、数据内容
        curatorFramework.create().forPath("/xx", "xr".getBytes());
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/q1/q2", "ganen".getBytes());

        //非空目录不能删除
        //curatorFramework.delete().forPath("/q1");

        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/q1");

        curatorFramework.close();
    }

    /**
     * 触发事件为创建节点和更新节点有效
     */
    @Test
    public void watch() throws Exception {

        //1 创建重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(2000, 3);
        //2 创建CuratorFramework对象
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .retryPolicy(retryPolicy)
                .connectString("192.168.95.22:2181,192.168.95.22:2182,192.168.95.22:2183")
                .sessionTimeoutMs(3000)
                .build();

        //3 开启启动
        curatorFramework.start();
        //创建缓存节点,第三个参数为是否压缩
        final NodeCache nodeCache = new NodeCache(curatorFramework, "/qf4", false);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.println("节点：" + nodeCache.getCurrentData().getPath());
                System.out.println("数据：" + new String(nodeCache.getCurrentData().getData()));
                System.out.println("状态：" + nodeCache.getCurrentData().getStat());
            }
        });

        Thread.sleep(1000);
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/qf4", "测试zookeeper的监听_创建".getBytes());
        Thread.sleep(1000);
        curatorFramework.setData().forPath("/qf4", "测试zookeeper的监听_修改".getBytes());
        Thread.sleep(1000);
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/qf4");
        Thread.sleep(1000);
    }

}
