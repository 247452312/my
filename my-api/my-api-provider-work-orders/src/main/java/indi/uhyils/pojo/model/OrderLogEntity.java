package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderLogEntity extends BaseVoEntity {

    /**
     * 处理人id(冗余)
     */
    private String dealUserId;

    /**
     * 错误原因(只要不是处理成功就要填写此列)
     */
    private String faultInfo;

    /**
     * 处理结果0->处理成功 1->处理失败 2->处理终止 3->已转交
     */
    private Integer resultStatus;

    /**
     * 监管人id(冗余)
     */
    private String monitorUserId;

    /**
     * 主表id
     */
    private String infoId;

    /**
     * 节点id
     */
    private String nodeId;


    public String getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    public String getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getMonitorUserId() {
        return monitorUserId;
    }

    public void setMonitorUserId(String monitorUserId) {
        this.monitorUserId = monitorUserId;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }


}
