package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * 订单流程图节点
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月27日 01时06分
 */
public class OrderFlowNodeEntity extends BaseVoEntity {

    /**
     * 此步骤是否必要,0->不必要 1->必要 0也可代表抄送
     */
    private Boolean necessary;

    /**
     * 处理结果附件id
     */
    private String enclosureId;

    /**
     * 处理意见以及结果 (html形式)
     */
    private String result;

    /**
     * 此步处理人id
     */
    private String responseUserId;

    /**
     * 工单id
     */
    private String orderId;

    /**
     * 此步骤处理状态 0->未受理 1->已受理 2->已解决 3->以跳过 4->以拒绝
     */
    private Integer status;


    public Boolean getNecessary() {
        return necessary;
    }

    public String getEnclosureId() {
        return enclosureId;
    }

    public String getResult() {
        return result;
    }

    public String getResponseUserId() {
        return responseUserId;
    }

    public String getOrderId() {
        return orderId;
    }

    public Integer getStatus() {
        return status;
    }


    public void setNecessary(Boolean necessary) {
        this.necessary = necessary;
    }

    public void setEnclosureId(String enclosureId) {
        this.enclosureId = enclosureId;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setResponseUserId(String responseUserId) {
        this.responseUserId = responseUserId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
