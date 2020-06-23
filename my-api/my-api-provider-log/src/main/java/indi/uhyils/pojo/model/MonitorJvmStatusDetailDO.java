package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseIdEntity;

/**
 * JVM实时信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 14时18分
 */
public class MonitorJvmStatusDetailDO extends BaseIdEntity {

    private String fid;

    /**
     * 已使用总内存
     */
    private Double useMem;

    /**
     * 堆区已使用内存
     */
    private Double heapUseMem;

    /**
     * 栈已使用内存
     */
    private Double noHeapUseMem;

    /**
     * status生成时间
     */
    private Long time;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Double getUseMem() {
        return useMem;
    }

    public void setUseMem(Double useMem) {
        this.useMem = useMem;
    }

    public Double getHeapUseMem() {
        return heapUseMem;
    }

    public void setHeapUseMem(Double heapUseMem) {
        this.heapUseMem = heapUseMem;
    }

    public Double getNoHeapUseMem() {
        return noHeapUseMem;
    }

    public void setNoHeapUseMem(Double noHeapUseMem) {
        this.noHeapUseMem = noHeapUseMem;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
