package indi.uhyils.pojo.DO;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
public class TraceInfoDO extends TraceIdDoDO {


    /**
     * 其他
     */
    private String other;

    /**
     * 日志等级
     */
    private String level;

    /**
     * 使用时间
     */
    private Long useTime;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 日志类型
     */
    private Integer logType;

    /**
     * ip
     */
    private String ip;

    /**
     * 此条hash值
     */
    private String hashCode;

    /**
     *
     */
    private String rpcId;

    /**
     * 线程名称
     */
    private String threadName;

    /**
     * 日志名称
     */
    private String loggerName;

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getRpcId() {
        return rpcId;
    }

    public void setRpcId(String rpcId) {
        this.rpcId = rpcId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
