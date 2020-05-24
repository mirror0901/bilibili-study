package com.mirror.flink.wordcount.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-05-13 00:00
 **/
public class HdfsClientDemo01 {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        //1.客户端加载配置文件
        Configuration conf = new Configuration();
        //2.指定配置（设置成2个副本数）
        //conf.set("dfs.replication", "1");
        conf.set("dfs.replication", "3");
        //3.指定块大小
        conf.set("dfs.blocksize", "64m");
        //4.构造客户端
        FileSystem fs = FileSystem.get(new URI("hdfs://192.168.95.22:8022/"), conf, "root");
        //5.上传文件
        fs.copyFromLocalFile(new Path("C:\\data\\wordcount.txt"), new Path("/words5.txt"));

        //6.关闭资源
        fs.close();

    }

}
