package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * JVM状态子表(LogMonitorJvmStatus)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时39分31秒
 */
@TableName(value = "sys_log_monitor_jvm_status")
public class LogMonitorJvmStatusDO extends BaseDO {

    private static final long serialVersionUID = 337721994440243487L;


    /**
     * 主表id
     */
    @TableField
    private Long fid;

    /**
     * status生成时间
     */
    @TableField
    private Long time;

    /**
     * 堆 使用内存
     */
    @TableField
    private Double heapUseMem;

    /**
     * 非堆区使用内存
     */
    @TableField
    private Double noHeapUseMem;

    /**
     * 总使用内存
     */
    @TableField
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
