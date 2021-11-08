package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * JVM状态子表(LogMonitorJvmStatus)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
public class LogMonitorJvmStatusDTO extends IdDTO {

    private static final long serialVersionUID = 972225360239528926L;


    /**
     * 主表id
     */
    private Long fid;

    /**
     * status生成时间
     */
    private Long time;

    /**
     * 堆 使用内存
     */
    private Double heapUseMem;

    /**
     * 非堆区使用内存
     */
    private Double noHeapUseMem;

    /**
     * 总使用内存
     */
    private Double useMem;


    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
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

    public Double getUseMem() {
        return useMem;
    }

    public void setUseMem(Double useMem) {
        this.useMem = useMem;
    }
}
