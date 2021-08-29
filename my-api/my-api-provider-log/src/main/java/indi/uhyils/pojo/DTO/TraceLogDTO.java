package indi.uhyils.pojo.DTO;


/**
 * (TraceLog)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
public class TraceLogDTO extends IdDTO {

    private static final long serialVersionUID = 566184677223809006L;


    /**
     * 日志名称
     */
    private String loggerName;

    /**
     * 日志等级
     */
    private String logLevel;

    private Long traceId;

    private String rpcId;

    private Long nowTime;

    private String log;


    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }


    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
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


    public Long getNowTime() {
        return nowTime;
    }

    public void setNowTime(Long nowTime) {
        this.nowTime = nowTime;
    }


    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

}
