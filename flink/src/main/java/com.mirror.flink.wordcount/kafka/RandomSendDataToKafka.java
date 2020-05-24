package com.mirror.flink.wordcount.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-05-17 19:02
 **/
public class RandomSendDataToKafka {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "docker2:32768");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        final Random random = new Random();
        //死循环生成键值对的数据
        while (true) {
            final ProducerRecord<String, String> data = new ProducerRecord<String, String>("flink", "key" + random.nextInt(10), "value" + random.nextInt(100));
            producer.send(data);
            Thread.sleep(1000);
        }
//        producer.close();

    }

}
