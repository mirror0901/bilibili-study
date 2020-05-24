package com.mirror.flink.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-05-17 03:58
 **/
public class SendDataToKafka {
    public static void main(String[] args) {
        SendDataToKafka sendDataToKafka = new SendDataToKafka();
        sendDataToKafka.send("test", "", "this is a test data too");
    }

    public void send(String topic, String key, String data) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.95.22:32768");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 1; i < 2; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            producer.send(new ProducerRecord<String, String>(topic, "" + i, data));
        }
        producer.close();
    }
}
