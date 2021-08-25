package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDoDO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderBaseNodeRouteDO extends BaseDoDO {

    /**
     * 上一个节点id
     */
    private Long prevNodeId;

    /**
     * 对应结果类型(单个)
     */
    private Long resultId;

    /**
     * 下一个节点id
     */
    private Long nextNodeId;


    public Long getPrevNodeId() {
        return prevNodeId;
    }

    public void setPrevNodeId(Long prevNodeId) {
        this.prevNodeId = prevNodeId;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Long getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(Long nextNodeId) {
        this.nextNodeId = nextNodeId;
    }
}
