package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 工单节点处理结果样例表(OrderNodeResultType)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分32秒
 */
public class OrderNodeResultTypeDTO extends IdDTO {

    private static final long serialVersionUID = -69618697510015672L;


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
