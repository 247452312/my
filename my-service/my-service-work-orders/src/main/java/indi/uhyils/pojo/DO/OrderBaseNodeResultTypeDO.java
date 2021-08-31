package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 工单节点处理结果样例表(OrderBaseNodeResultType)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分03秒
 */
public class OrderBaseNodeResultTypeDO extends BaseDO {

    private static final long serialVersionUID = 720441827729659519L;


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
