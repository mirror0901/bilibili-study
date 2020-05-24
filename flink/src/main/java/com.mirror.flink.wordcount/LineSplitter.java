package com.mirror.flink.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-05-09 01:06
 **/
public class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
        // 统一大小写，并切割
        String[] tokens = value.toLowerCase().split("\\W+");
        // 构造成(word,1)的格式
        for (String token : tokens) {
            if (token.length() > 0) {
                out.collect(new Tuple2<String, Integer>(token, 1));
            }
        }
    }

}
