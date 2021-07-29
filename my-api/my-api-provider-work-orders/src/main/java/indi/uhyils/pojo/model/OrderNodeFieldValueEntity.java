package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoEntity;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月15日 16时18分51秒
 */
public class OrderNodeFieldValueEntity extends BaseDoEntity {

    private static final long serialVersionUID = -40521694532478838L;


    /**
     * 对应节点属性的id
     */
    private Long nodeFieldId;

    /**
     * 真实值
     */
    private String realValue;


    public Long getNodeFieldId() {
        return nodeFieldId;
    }

    public void setNodeFieldId(Long nodeFieldId) {
        this.nodeFieldId = nodeFieldId;
    }

    public String getRealValue() {
        return realValue;
    }

    public void setRealValue(String realValue) {
        this.realValue = realValue;
    }

}
