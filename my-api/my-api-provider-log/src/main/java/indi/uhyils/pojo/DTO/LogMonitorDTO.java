package indi.uhyils.pojo.DTO;


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
    private Object jvmTotalMem;

    /**
     * 堆初始内存
     */
    private Object heapInitMem;

    /**
     * 堆最大内存
     */
    private Object heapTotalMem;

    /**
     * 非堆区初始内存
     */
    private Object noHeapInitMem;

    /**
     * 非堆区最大内存
     */
    private Object noHeapTotalMem;

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


    public Object getJvmTotalMem() {
        return jvmTotalMem;
    }

    public void setJvmTotalMem(Object jvmTotalMem) {
        this.jvmTotalMem = jvmTotalMem;
    }


    public Object getHeapInitMem() {
        return heapInitMem;
    }

    public void setHeapInitMem(Object heapInitMem) {
        this.heapInitMem = heapInitMem;
    }


    public Object getHeapTotalMem() {
        return heapTotalMem;
    }

    public void setHeapTotalMem(Object heapTotalMem) {
        this.heapTotalMem = heapTotalMem;
    }


    public Object getNoHeapInitMem() {
        return noHeapInitMem;
    }

    public void setNoHeapInitMem(Object noHeapInitMem) {
        this.noHeapInitMem = noHeapInitMem;
    }


    public Object getNoHeapTotalMem() {
        return noHeapTotalMem;
    }

    public void setNoHeapTotalMem(Object noHeapTotalMem) {
        this.noHeapTotalMem = noHeapTotalMem;
    }


    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

}
