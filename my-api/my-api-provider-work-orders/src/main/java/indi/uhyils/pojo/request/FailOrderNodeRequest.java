package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时30分
 */
public class FailOrderNodeRequest extends DefaultRequest {

    /**
     * 工单节点失败
     */
    private Long orderNodeId;

    /**
     * 失败原因
     */
    private String msg;

    public Long getOrderNodeId() {
        return orderNodeId;
    }

    public void setOrderNodeId(Long orderNodeId) {
        this.orderNodeId = orderNodeId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
