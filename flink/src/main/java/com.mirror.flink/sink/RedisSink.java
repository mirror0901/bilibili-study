package com.mirror.flink.sink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description: 需求 把netcat作为数据源，并且统计每个单词的次数，统计结果写入redis数据库中
 * @reference: https://blog.csdn.net/FBB360JAVA/article/details/103452748
 * @create: 2020-05-25 01:22
 **/
public class RedisSink {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(1);

        //读取数据源
        final DataStreamSource<String> dataStream = executionEnvironment.fromElements("feng", "li", "zhao", "liu", "hu");

        //连接redis配置
        final FlinkJedisPoolConfig redisConfig = new FlinkJedisPoolConfig.Builder().setDatabase(1).setHost("docker3").setPort(6379).setPassword("123456").build();

        //创建RedisSink对象
        dataStream.map(
                (MapFunction<String, Tuple2<String, String>>) value -> new Tuple2("userSets", value)).returns(Types.TUPLE(Types.STRING, Types.STRING)
        ).addSink(new org.apache.flink.streaming.connectors.redis.RedisSink(redisConfig, new RedisMapperImpl()));

        executionEnvironment.execute("redisJob");

        //打印结果
        Jedis jedis = new Jedis("192.168.95.23", 6379);
        jedis.auth("123456");
        jedis.select(1);
        jedis.smembers("userSets").forEach(System.out::println);
    }

    private static class RedisMapperImpl implements RedisMapper<Tuple2<String, String>> {

        private static final long serialVersionUID = -8714885560557692676L;

        /**
         * 设置数据使用的数据结构为 set；即redis中命令为sadd
         *
         * @return RedisCommandDescription
         */
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.SADD, "userSets");
        }

        /**
         * 映射key
         *
         * @param stringLongTuple2 数据
         * @return key
         */
        public String getKeyFromData(Tuple2<String, String> stringLongTuple2) {
            return stringLongTuple2.f0;
        }

        /**
         * 映射value
         *
         * @param stringLongTuple2 数据
         * @return value
         */
        public String getValueFromData(Tuple2<String, String> stringLongTuple2) {
            return stringLongTuple2.f1;
        }
    }

}
