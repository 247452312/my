package indi.uhyils.pojo.model;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
public class TraceLogDO extends TraceIdDoDO {

    /**
     *
     */
    private Long nowTime;


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
