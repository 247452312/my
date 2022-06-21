package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * JVM日志表(LogMonitor)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
public class LogMonitorDTO extends IdDTO {

    private static final long serialVersionUID = -44180870046046199L;


    /**
     * ip
     */
    private String ip;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * jvm开启时间
     */
    private Long time;

    /**
     * jvm最大内存
     */
    private Double jvmTotalMem;

    /**
     * 堆初始内存
     */
    private Double heapInitMem;

    /**
     * 堆最大内存
     */
    private Double heapTotalMem;

    /**
     * 非堆区初始内存
     */
    private Double noHeapInitMem;

    /**
     * 非堆区最大内存
     */
    private Double noHeapTotalMem;

    /**
     * 服务jvm结束假想时间
     */
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
