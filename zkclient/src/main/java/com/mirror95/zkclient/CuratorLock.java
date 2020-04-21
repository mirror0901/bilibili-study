package com.mirror95.zkclient;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description: curator实现分布式锁
 * @create: 2020-02-01 14:56
 **/
public class CuratorLock {

    @Test
    public void testLock() throws Exception {
        //1 创建重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(2000, 3);
        //2 创建curatorFramework对象
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.95.22:2181")
                .sessionTimeoutMs(6000)
                .retryPolicy(retryPolicy)
                .build();
        //3 开启链接
        curatorFramework.start();
        //4 分布式锁
        //注意curatorLock是一个持久节点,不同的进程会在这个节点下面创建临时节点
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/curatorLock");

        //创建线程池缓存
        ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors() * 2,
                10,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(30),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        Consumer<InterProcessMutex> consumer = (InterProcessMutex interProcessMutex) -> {
            try {
                List<Callable<String>> callableList = new ArrayList<>();
                Callable<String> callable = () -> {
                    try {
                        //获取锁
                        interProcessMutex.acquire();
                        System.out.println(Thread.currentThread() + " acquire read lock ");
                        //模拟业务场景
                        Thread.sleep(3000);
                        System.out.println("正在处理业务");
                        //编写业务结束
                    } finally {
                        //释放锁
                        interProcessMutex.release();
                        System.out.println(Thread.currentThread() + " release read lock ");
                    }
                    return "true";
                };
                //并发线程
                for (int i = 0; i < 10; i++) {
                    callableList.add(callable);
                    //为了查看在 curatorLock 下面有临时节点
                    Thread.sleep(100);
                }
                List<Future<String>> futures = executorService.invokeAll(callableList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        //分布式锁测试
        consumer.accept(lock);
        executorService.shutdown();
        curatorFramework.close();
    }


    @Test
    public void testThreadNum() {
        System.out.println("cpu核心数:" + Runtime.getRuntime().availableProcessors());
    }

}
