package indi.uhyils.pojo.DTO;


/**
 * 日志表(OrderLog)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分18秒
 */
public class OrderLogDTO extends IdDTO {

    private static final long serialVersionUID = 983000921649248296L;


    /**
     * 主表id
     */
    private Long infoId;

    /**
     * 节点id
     */
    private Long nodeId;

    /**
     * 处理人id(冗余)
     */
    private Long dealUserId;

    /**
     * 监管人id(冗余)
     */
    private Long monitorUserId;

    /**
     * 处理结果0->处理成功 1->处理失败 2->处理终止 3->已转交
     */
    private Integer resultStatus;

    /**
     * 错误原因(只要不是处理成功就要填写此列)
     */
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
