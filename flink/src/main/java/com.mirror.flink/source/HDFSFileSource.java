package com.mirror.flink.source;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-05-12 21:36
 * TODO:未试验成功
 **/
public class HDFSFileSource {

    public static void main(String[] args) throws Exception {
        //1.初始化流计算的环境
        final StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        //executionEnvironment.setParallelism(1);
        //2.读取HDFS文件系统上的文件
        final DataStreamSource<String> stringDataStreamSource = executionEnvironment.readTextFile("hdfs://docker2:8022/rmstate/FSRMStateRoot/EpochNode");

        //单词统计的计算
        final SingleOutputStreamOperator<Tuple2<String, Integer>> sum = stringDataStreamSource.flatMap(new Splitter())
                .keyBy(0)
                .timeWindow(Time.seconds(5))
                .sum(1);

        //定义sink
        sum.print("结果");
        executionEnvironment.execute("hdfswordCount");

    }

    public static class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            for (String word : s.split(" ")) {
                collector.collect(new Tuple2<String, Integer>(word, 1));
            }
        }

    }

}
