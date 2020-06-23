package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseIdEntity;

/**
 * JVM监控信息
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 14时06分
 */
public class MonitorDO extends BaseIdEntity {

    private String serviceName;

    private String ip;

    /**
     * jvm开启时间
     */
    private Long time;

    /**
     * 假想结束时间
     */
    private Long endTime;

    /**
     * jvm总分配内存
     */
    private Double jvmTotalMem;

    /**
     * jvm 堆分配最大内存
     */
    private Double heapTotalMem;

    /**
     * jvm 堆初始内存
     */
    private Double heapInitMem;

    /**
     * jvm 非堆区分配最大内存
     */
    private Double noHeapTotalMem;

    /**
     * jvm非堆区初始内存
     */
    private Double noHeapInitMem;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Double getJvmTotalMem() {
        return jvmTotalMem;
    }

    public void setJvmTotalMem(Double jvmTotalMem) {
        this.jvmTotalMem = jvmTotalMem;
    }

    public Double getHeapTotalMem() {
        return heapTotalMem;
    }

    public void setHeapTotalMem(Double heapTotalMem) {
        this.heapTotalMem = heapTotalMem;
    }

    public Double getHeapInitMem() {
        return heapInitMem;
    }

    public void setHeapInitMem(Double heapInitMem) {
        this.heapInitMem = heapInitMem;
    }

    public Double getNoHeapTotalMem() {
        return noHeapTotalMem;
    }

    public void setNoHeapTotalMem(Double noHeapTotalMem) {
        this.noHeapTotalMem = noHeapTotalMem;
    }

    public Double getNoHeapInitMem() {
        return noHeapInitMem;
    }

    public void setNoHeapInitMem(Double noHeapInitMem) {
        this.noHeapInitMem = noHeapInitMem;
    }
}
