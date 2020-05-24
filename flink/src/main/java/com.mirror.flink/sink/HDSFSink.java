package com.mirror.flink.sink;

import com.mirror.flink.source.MyCustomerSource;
import com.mirror.flink.source.StationLog;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description: 需求:把自定义的source作为数据源，把基站日志数据写入HDFS并且每隔两秒钟生成一个文件
 * @create: 2020-05-25 00:05
 **/
public class HDSFSink {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(3);
        //读取数据源
        final DataStreamSource<StationLog> stationLogDataStreamSource = executionEnvironment.addSource(new MyCustomerSource());
        //默认一个小时一个目录（分桶)
        //设置一个滚动策略
        final DefaultRollingPolicy<StationLog, String> policy = DefaultRollingPolicy
                .create()
                //不活动的分桶时间
                .withInactivityInterval(2000)
                //每隔两秒生成一个文件
                .withRolloverInterval(2000)
                .build();

        //创建HDFS的Sink
        final StreamingFileSink<StationLog> sink = StreamingFileSink.forRowFormat(
                new Path("hdfs://docker2:8022/MySink001/"),
                new SimpleStringEncoder<StationLog>("UTF-8")
        ).withRollingPolicy(policy)
                //检查间隔时间
                .withBucketCheckInterval(1000)
                .build();

        stationLogDataStreamSource.addSink(sink);
        executionEnvironment.execute();
    }

}
