package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
public class TraceLogEntity extends BaseDoEntity {

    /**
     *
     */
    private Long nowTime;

    /**
     *
     */
    private Long traceId;

    /**
     *
     */
    private String rpcId;

    /**
     *
     */
    private String log;

    /**
     * 日志等级
     */
    private String logLevel;

    /**
     * 日志名称
     */
    private String loggerName;


    public Long getNowTime() {
        return nowTime;
    }

    public void setNowTime(Long nowTime) {
        this.nowTime = nowTime;
    }

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public String getRpcId() {
        return rpcId;
    }

    public void setRpcId(String rpcId) {
        this.rpcId = rpcId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }


}
