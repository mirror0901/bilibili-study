package com.mirror.flink.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description: flink流计算的word count
 * @create: 2020-05-10 23:04
 **/
public class WordCountStream {

    public static void main(String[] args) throws Exception {
        //1.初始化流计算的环境
        final StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(1);
        //2.读取数据，读取sock流中的数据
        final DataStream<String> dataStream = executionEnvironment.socketTextStream("192.168.95.22", 8888);
        //3.转换和处理数据
        final SingleOutputStreamOperator<Tuple2<String, Integer>> result = dataStream.flatMap(new Splitter())
                .keyBy(0) //分组算子 0或1表示下标
                .timeWindow(Time.seconds(5))
                .sum(1);//聚合累加算子
        //4.打印结果
        result.print("结果");
        //5.启动流计算程序
        executionEnvironment.execute("wordCount Stream");

    }

    public static class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            for (String word : s.split(" ")) {
                collector.collect(new Tuple2<String, Integer>(word, 1));
            }
        }
    }

}
