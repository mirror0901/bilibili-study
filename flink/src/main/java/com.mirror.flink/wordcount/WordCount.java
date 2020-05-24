package com.mirror.flink.wordcount;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-05-08 23:21
 **/
public class WordCount {
    public static void main(String[] args) throws Exception {

        //初始化Flink运行环境
        final ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();

        //测试数据
        final DataSource<String> text = executionEnvironment.fromElements(
                "To be, or not to be,--that is the question:--",
                "Whether 'tis nobler in the mind to suffer",
                "The slings and arrows of outrageous fortune",
                "Or to take arms against a sea of troubles,");

        //执行wordcount
        DataSet<Tuple2<String, Integer>> counts = text.flatMap(new LineSplitter())
                .groupBy(0)
                .sum(1);
        counts.print();

    }
}
