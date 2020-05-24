package com.mirror.flink.wordcount.source;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-05-15 02:08
 **/
public class StationLog {

    private String sid;
    private String callOut;
    private String callIn;
    private String callType;
    private String callTime;
    private int duration;

    public StationLog() {
    }

    public StationLog(String sid, String callOut, String callIn, String callType, String callTime, int duration) {
        this.sid = sid;
        this.callOut = callOut;
        this.callIn = callIn;
        this.callType = callType;
        this.callTime = callTime;
        this.duration = duration;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCallOut() {
        return callOut;
    }

    public void setCallOut(String callOut) {
        this.callOut = callOut;
    }

    public String getCallIn() {
        return callIn;
    }

    public void setCallIn(String callIn) {
        this.callIn = callIn;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "StationLog{" +
                "sid='" + sid + '\'' +
                ", callOut='" + callOut + '\'' +
                ", callIn='" + callIn + '\'' +
                ", callType='" + callType + '\'' +
                ", callTime='" + callTime + '\'' +
                ", duration=" + duration +
                '}';
    }
}
