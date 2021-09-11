package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * JVM日志表(LogMonitor)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时39分30秒
 */
@TableName(value = "sys_log_monitor")
public class LogMonitorDO extends BaseDO {

    private static final long serialVersionUID = -67844554285245726L;


    /**
     * ip
     */
    @TableField
    private String ip;

    /**
     * 服务名称
     */
    @TableField
    private String serviceName;

    /**
     * jvm开启时间
     */
    @TableField
    private Long time;

    /**
     * jvm最大内存
     */
    @TableField
    private Double jvmTotalMem;

    /**
     * 堆初始内存
     */
    @TableField
    private Double heapInitMem;

    /**
     * 堆最大内存
     */
    @TableField
    private Double heapTotalMem;

    /**
     * 非堆区初始内存
     */
    @TableField
    private Double noHeapInitMem;

    /**
     * 非堆区最大内存
     */
    @TableField
    private Double noHeapTotalMem;

    /**
     * 服务jvm结束假想时间
     */
    @TableField
    private Long endTime;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


    public Double getJvmTotalMem() {
        return jvmTotalMem;
    }

    public void setJvmTotalMem(Double jvmTotalMem) {
        this.jvmTotalMem = jvmTotalMem;
    }


    public Double getHeapInitMem() {
        return heapInitMem;
    }

    public void setHeapInitMem(Double heapInitMem) {
        this.heapInitMem = heapInitMem;
    }


    public Double getHeapTotalMem() {
        return heapTotalMem;
    }

    public void setHeapTotalMem(Double heapTotalMem) {
        this.heapTotalMem = heapTotalMem;
    }


    public Double getNoHeapInitMem() {
        return noHeapInitMem;
    }

    public void setNoHeapInitMem(Double noHeapInitMem) {
        this.noHeapInitMem = noHeapInitMem;
    }


    public Double getNoHeapTotalMem() {
        return noHeapTotalMem;
    }

    public void setNoHeapTotalMem(Double noHeapTotalMem) {
        this.noHeapTotalMem = noHeapTotalMem;
    }


    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

}
