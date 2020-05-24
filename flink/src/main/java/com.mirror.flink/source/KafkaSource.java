package com.mirror.flink.source;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.codehaus.jackson.map.deser.std.StringDeserializer;

import java.util.Properties;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-05-17 02:17
 **/
public class KafkaSource {

    public static void main(String[] args) throws Exception {
        //1.初始化流计算的环境
        final StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(2);
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "docker2:32768");
        properties.setProperty("group.id", "flink");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("auto.offset.reset", "latest");
        final DataStreamSource<String> flink = executionEnvironment.addSource(new FlinkKafkaConsumer<String>("flink", new SimpleStringSchema(), properties));
        flink.print();
        executionEnvironment.execute();
    }

}
