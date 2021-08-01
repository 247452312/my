package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年07月31日 06时43分
 */
public class TraceInfoEntity extends BaseDoEntity {


    /**
     *
     */
    private Long traceId;

    /**
     * 其他
     */
    private String other;

    /**
     * 日志名称
     */
    private String logName;

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
     * hash码
     */
    private String hashCode;

    /**
     * 日志类型
     */
    private Integer logType;

    /**
     *
     */
    private String rpcId;

    /**
     * 线程名称
     */
    private String threadName;

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }
}