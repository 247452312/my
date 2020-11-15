package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderNodeRouteEntity extends BaseVoEntity {

    /**
     * 上一个节点id
     */
    private String prevNodeId;

    /**
     * 对应结果类型(单个)
     */
    private String resultId;

    /**
     * 下一个节点id
     */
    private String nextNodeId;

    public String getPrevNodeId() {
        return prevNodeId;
    }

    public void setPrevNodeId(String prevNodeId) {
        this.prevNodeId = prevNodeId;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(String nextNodeId) {
        this.nextNodeId = nextNodeId;
    }


}
