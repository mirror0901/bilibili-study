package com.mirror95.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-01-19 23:07
 **/
public class TestZK01 {

    public static void main(String[] args) {

    }

    @Test
    public void testCreate() {

        //创建Zookeeper的客户端对象
        ZkClient zkClient = new ZkClient("192.168.95.22:2181,192.168.95.22:2182,192.168.95.22:2183");
        //创建一个不存在的节点
        zkClient.create("/baike/1", "www.mirror95.top", CreateMode.PERSISTENT_SEQUENTIAL);
        zkClient.close();

    }

    @Test
    public void delete() {
        //创建Zookeeper的客户端对象
        ZkClient zkClient = new ZkClient("192.168.95.22:2181");
        zkClient.deleteRecursive("/baike1");
    }

    /**
     * 监听方法
     */
    @Test
    public void watcher() throws Exception {
        //创建zookeeper的客户端对象
        ZkClient zkClient = new ZkClient("192.168.95.22:2181");
        zkClient.create("/baike1", "java", CreateMode.PERSISTENT);
        zkClient.subscribeDataChanges("/baike1", new IZkDataListener() {

            //@Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("路径 " + s + " 下的值被修改为: " + o);
            }

            //@Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("路径 " + s + " 被删除");
            }

        });

        zkClient.writeData("/baike1", "hello world");
        Thread.sleep(1000);

        boolean f = zkClient.deleteRecursive("/baike1");
        Thread.sleep(1000);
        System.out.println(f ? "递归删除成功" : "递归删除失败");
        Thread.sleep(1000);
        zkClient.close();
    }

}
