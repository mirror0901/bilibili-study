package com.mirror.flink.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.net.URL;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-05-11 00:08
 **/
public class WordCountBatch {

    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
        final DataSource<String> data = executionEnvironment.readTextFile(new WordCountBatch().getClassLoad());
        data.flatMap(new Splitter())
                .groupBy(0)
                .sum(1)
                .print();

    }

    public String getClassLoad() {
        final ClassLoader classLoader = getClass().getClassLoader();
        final URL resource = classLoader.getResource("word.txt");
        System.out.println(resource);
        return resource.toString();
    }

    public static class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            for (String word : s.split(" ")) {
                collector.collect(new Tuple2<String, Integer>(word, 1));
            }
        }
    }

}
