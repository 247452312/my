package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDoDO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderBaseNodeResultTypeDO extends BaseDoDO {

    /**
     * 节点id
     */
    private Long baseNodeId;

    /**
     * 处理结果名称
     */
    private String dealResultName;


    public Long getBaseNodeId() {
        return baseNodeId;
    }

    public void setBaseNodeId(Long baseNodeId) {
        this.baseNodeId = baseNodeId;
    }

    public String getDealResultName() {
        return dealResultName;
    }

    public void setDealResultName(String dealResultName) {
        this.dealResultName = dealResultName;
    }


}
