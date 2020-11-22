package indi.uhyils.pojo.temp;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 17时12分
 */
public class InitApiRequestTemporary {

    /**
     * 工单节点id
     */
    private String orderNodeId;

    /**
     * 上一个节点的id
     */
    private String pervOrderNodeId;

    public String getOrderNodeId() {
        return orderNodeId;
    }

    public void setOrderNodeId(String orderNodeId) {
        this.orderNodeId = orderNodeId;
    }

    public String getPervOrderNodeId() {
        return pervOrderNodeId;
    }

    public void setPervOrderNodeId(String pervOrderNodeId) {
        this.pervOrderNodeId = pervOrderNodeId;
    }
}
