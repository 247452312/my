package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.cqe.command.AbstractCommand;

/**
 * 申请失败转交节点请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月25日 07时56分
 */
public class IncapacityFailOrderNodeCommand extends AbstractCommand {

    /**
     * 节点id
     */
    private Long orderNodeId;

    /**
     * 失败原因
     */
    private String reasons;

    /**
     * 推荐人id
     */
    private Long recommendUserId;

    public Long getOrderNodeId() {
        return orderNodeId;
    }

    public void setOrderNodeId(Long orderNodeId) {
        this.orderNodeId = orderNodeId;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public Long getRecommendUserId() {
        return recommendUserId;
    }

    public void setRecommendUserId(Long recommendUserId) {
        this.recommendUserId = recommendUserId;
    }
}
