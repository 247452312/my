package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * (TraceInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时39分32秒
 */
@TableName(value = "sys_trace_info")
public class TraceInfoDO extends BaseDO {

    private static final long serialVersionUID = 931060122256618517L;


    /**
     * 日志名称
     */
    @TableField
    private String loggerName;

    /**
     * 日志等级
     */
    @TableField
    private String level;

    @TableField
    private Long traceId;

    /**
     * 开始时间
     */
    @TableField
    private Long startTime;

    /**
     * 日志类型
     */
    @TableField
    private Integer logType;

    /**
     * ip
     */
    @TableField
    private String ip;

    @TableField
    private String rpcId;

    /**
     * 线程名称
     */
    @TableField
    private String threadName;

    /**
     * 项目名称
     */
    @TableField
    private String projectName;

    /**
     * 使用时间
     */
    @TableField
    private Long useTime;

    /**
     * 此条hash值
     */
    @TableField
    private String hashCode;

    /**
     * 其他
     */
    @TableField
    private String other;


    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
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


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }


    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }


    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

}
