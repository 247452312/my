package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoDO;

/**
 * 每一个 -->微服务的集群<-- 中的每一个 -->微服务<-- 都会有这么一条JVM监控信息数据
 * {@db sys_MonitorDO}
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 14时06分
 */
public class LogMonitorDO extends BaseDoDO {

    /**
     * 微服务的名称 名称与微服务rpc的applicationName相同
     */
    private String serviceName;

    /**
     * 微服务所在服务器的访问ip
     */
    private String ip;

    /**
     * jvm开启时间
     */
    private Long time;

    /**
     * 假想结束时间 默认是往后推30分钟,如果不更新则此处的结束
     * 时间变成了真实的结束时间,也就是说,此条记录是静态的,并没
     * 有储存微服务现在的状态,但是依旧可以通过endTime有没有超
     * 出来判断微服务是否在半小时内离线
     */
    private Long endTime;

    /**
     * jvm总分配内存(堆内存 + 堆外内存)
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("            \"serviceName\":\"")
          .append(serviceName).append('\"');
        if (ip != null) {
            sb.append(",            \"ip\":\"")
              .append(ip).append('\"');
        }
        if (time != null) {
            sb.append(",            \"time\":")
              .append(time);
        }
        if (endTime != null) {
            sb.append(",            \"endTime\":")
              .append(endTime);
        }
        if (jvmTotalMem != null) {
            sb.append(",            \"jvmTotalMem\":")
              .append(jvmTotalMem);
        }
        if (heapTotalMem != null) {
            sb.append(",            \"heapTotalMem\":")
              .append(heapTotalMem);
        }
        if (heapInitMem != null) {
            sb.append(",            \"heapInitMem\":")
              .append(heapInitMem);
        }
        if (noHeapTotalMem != null) {
            sb.append(",            \"noHeapTotalMem\":")
              .append(noHeapTotalMem);
        }
        if (noHeapInitMem != null) {
            sb.append(",            \"noHeapInitMem\":")
              .append(noHeapInitMem);
        }
        sb.append('}');
        return sb.toString();
    }
}
