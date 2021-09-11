package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 日志表(OrderLog)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时46分06秒
 */
@TableName(value = "sys_order_log")
public class OrderLogDO extends BaseDO {

    private static final long serialVersionUID = 851502791971362500L;


    /**
     * 主表id
     */
    @TableField
    private Long infoId;

    /**
     * 节点id
     */
    @TableField
    private Long nodeId;

    /**
     * 处理人id(冗余)
     */
    @TableField
    private Long dealUserId;

    /**
     * 监管人id(冗余)
     */
    @TableField
    private Long monitorUserId;

    /**
     * 处理结果0->处理成功 1->处理失败 2->处理终止 3->已转交
     */
    @TableField
    private Integer resultStatus;

    /**
     * 错误原因(只要不是处理成功就要填写此列)
     */
    @TableField
    private String faultInfo;


    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }


    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }


    public Long getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(Long dealUserId) {
        this.dealUserId = dealUserId;
    }


    public Long getMonitorUserId() {
        return monitorUserId;
    }

    public void setMonitorUserId(Long monitorUserId) {
        this.monitorUserId = monitorUserId;
    }


    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }


    public String getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
    }

}
