package indi.uhyils.mq.pojo.mqinfo;

import indi.uhyils.pojo.cqe.command.AbstractCommand;

/**
 * 每个微服务定时任务中微服务的info 一下所有内存单位为m
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月18日 07时36分
 */
public class JvmStatusInfoCommand extends AbstractCommand {

    /**
     * jvm使用的总内存
     */
    private Double totalUseMem;

    /**
     * 堆以外的内存
     */
    private Double noHeapUseMem;

    /**
     * 堆占用的内存(类对象 成员变量)
     */
    private Double heapUseMem;

    /**
     * 此状态生成时间
     */
    private Long time;

    /**
     * 唯一标示
     */
    private JvmUniqueMark jvmUniqueMark;

    public Double getTotalUseMem() {
        return totalUseMem;
    }

    public void setTotalUseMem(Double totalUseMem) {
        this.totalUseMem = totalUseMem;
    }

    public Double getNoHeapUseMem() {
        return noHeapUseMem;
    }

    public void setNoHeapUseMem(Double noHeapUseMem) {
        this.noHeapUseMem = noHeapUseMem;
    }

    public Double getHeapUseMem() {
        return heapUseMem;
    }

    public void setHeapUseMem(Double heapUseMem) {
        this.heapUseMem = heapUseMem;
    }

    public JvmUniqueMark getJvmUniqueMark() {
        return jvmUniqueMark;
    }

    public void setJvmUniqueMark(JvmUniqueMark jvmUniqueMark) {
        this.jvmUniqueMark = jvmUniqueMark;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
