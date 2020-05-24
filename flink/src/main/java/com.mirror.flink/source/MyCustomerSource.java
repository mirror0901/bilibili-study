package com.mirror.flink.source;

import com.google.common.collect.Lists;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description: 自定义的Source需求:每隔两秒钟，生成10跳随机基站通话日志数据
 * @create: 2020-05-24 21:58
 **/
public class MyCustomerSource implements SourceFunction<StationLog> {

    /**
     * 是否终止数据流的标记
     */
    private boolean flag = true;

    /**
     * 主要的方法，启动一个Source，并且从Source中返回数据
     * 如果run方法停止，则数据流终止
     *
     * @param sourceContext
     * @throws Exception
     */
    public void run(SourceContext<StationLog> sourceContext) throws Exception {
        Random random = new Random();
        final ArrayList<String> types = Lists.newArrayList("fail", "basy", "barring", "success");
        while (flag) {
            for (int i = 0; i < 10; i++) {
                //主被叫号码
                final String callOut = String.format("1771265%04d", random.nextInt(9000) + 10000);
                //被叫号码
                final String callIn = String.format("1771265%04d", random.nextInt(9000) + 10000);
                //生成一条数据
                final StationLog stationLog = new StationLog("station_" + random.nextInt(10), callOut, callIn, types.get(random.nextInt(4)), System.currentTimeMillis() + "", random.nextInt(120));
                sourceContext.collect(stationLog);
            }
            //每隔2秒发送一次数据
            Thread.sleep(2000);
        }
    }

    /**
     * 终止数据流
     */
    public void cancel() {
        flag = false;
    }

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(1);
        final DataStreamSource<StationLog> stationLogDataStreamSource = executionEnvironment.addSource(new MyCustomerSource());
        stationLogDataStreamSource.print();
        executionEnvironment.execute("mirror's job");
    }

}
