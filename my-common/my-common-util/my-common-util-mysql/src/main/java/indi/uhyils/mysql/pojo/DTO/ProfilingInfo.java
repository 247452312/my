package indi.uhyils.mysql.pojo.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * information_schema.profiling可以用来分析每一条SQL在它执行的各个阶段的用时，注意这个表是session 级的，也就是说如果session1 开启了它；session2没有开启
 * <p>
 * 这个情况下session2 去查询只会返回一个空表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月08日 14时40分
 */
public class ProfilingInfo implements Serializable {

    /**
     * 查询id 用于标记不同的查询
     */
    private Integer queryId;

    /**
     * 一个查询内部执行的步骤
     */
    private Integer seq;

    /**
     * 各个步骤的状态
     */
    private String state;

    /**
     * 各个步骤持续的时间
     */
    private BigDecimal duration;

    /**
     * 用户空间的cpu 使用量
     */
    private BigDecimal cpuUser;

    /**
     * 内核空间的cpu 使用量
     */
    private BigDecimal cpuSystem;

    /**
     * 自愿上下文切换
     */
    private Integer contextVoluntary;

    /**
     * 非自愿上下文切换
     */
    private Integer contextInvoluntary;

    /**
     * 块调入次数
     */
    private Integer blockOpsIn;

    /**
     * 块调出次数
     */
    private Integer blockOpsOut;

    /**
     * 消息发送
     */
    private Integer messagesSent;

    /**
     * 消息接受
     */
    private Integer messagesReceived;

    /**
     * 主分页错误
     */
    private Integer pageFaultsMajor;

    /**
     * 次分页错误
     */
    private Integer pageFaultsMinor;

    /**
     * swap 发生的次数
     */
    private Integer swaps;

    /**
     * MySQL源码执行函数
     */
    private String sourceFunction;

    /**
     * 源码文件
     */
    private String sourceFile;

    /**
     * 源码行数
     */
    private Integer sourceLine;

    public Integer getQueryId() {
        return queryId;
    }

    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public BigDecimal getCpuUser() {
        return cpuUser;
    }

    public void setCpuUser(BigDecimal cpuUser) {
        this.cpuUser = cpuUser;
    }

    public BigDecimal getCpuSystem() {
        return cpuSystem;
    }

    public void setCpuSystem(BigDecimal cpuSystem) {
        this.cpuSystem = cpuSystem;
    }

    public Integer getContextVoluntary() {
        return contextVoluntary;
    }

    public void setContextVoluntary(Integer contextVoluntary) {
        this.contextVoluntary = contextVoluntary;
    }

    public Integer getContextInvoluntary() {
        return contextInvoluntary;
    }

    public void setContextInvoluntary(Integer contextInvoluntary) {
        this.contextInvoluntary = contextInvoluntary;
    }

    public Integer getBlockOpsIn() {
        return blockOpsIn;
    }

    public void setBlockOpsIn(Integer blockOpsIn) {
        this.blockOpsIn = blockOpsIn;
    }

    public Integer getBlockOpsOut() {
        return blockOpsOut;
    }

    public void setBlockOpsOut(Integer blockOpsOut) {
        this.blockOpsOut = blockOpsOut;
    }

    public Integer getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(Integer messagesSent) {
        this.messagesSent = messagesSent;
    }

    public Integer getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(Integer messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public Integer getPageFaultsMajor() {
        return pageFaultsMajor;
    }

    public void setPageFaultsMajor(Integer pageFaultsMajor) {
        this.pageFaultsMajor = pageFaultsMajor;
    }

    public Integer getPageFaultsMinor() {
        return pageFaultsMinor;
    }

    public void setPageFaultsMinor(Integer pageFaultsMinor) {
        this.pageFaultsMinor = pageFaultsMinor;
    }

    public Integer getSwaps() {
        return swaps;
    }

    public void setSwaps(Integer swaps) {
        this.swaps = swaps;
    }

    public String getSourceFunction() {
        return sourceFunction;
    }

    public void setSourceFunction(String sourceFunction) {
        this.sourceFunction = sourceFunction;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public Integer getSourceLine() {
        return sourceLine;
    }

    public void setSourceLine(Integer sourceLine) {
        this.sourceLine = sourceLine;
    }
}
