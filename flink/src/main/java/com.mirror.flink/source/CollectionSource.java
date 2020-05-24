package com.mirror.flink.source;


import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description: 从集合中读取数据源
 * @create: 2020-05-15 01:43
 **/
public class CollectionSource {

    public static void main(String[] args) throws Exception {
        List<StationLog> stationLogList = new ArrayList<StationLog>();
        stationLogList.add(new StationLog("001", "122121", "223421", "success", "233", 60));
        stationLogList.add(new StationLog("002", "122122", "223422", "fail", "2313", 62));
        stationLogList.add(new StationLog("003", "122123", "223423", "success", "2133", 61));
        stationLogList.add(new StationLog("004", "122124", "223424", "fail", "1233", 34));
        stationLogList.add(new StationLog("005", "122125", "223425", "success", "5233", 23));

        //1.初始化流计算的环境
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        final DataStreamSource<StationLog> stationLogDataStreamSource = executionEnvironment.fromCollection(stationLogList);
        stationLogDataStreamSource.print();
        executionEnvironment.execute("collectionJob");
    }

}