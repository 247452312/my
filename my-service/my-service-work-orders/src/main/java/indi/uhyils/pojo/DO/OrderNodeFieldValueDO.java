package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分26秒
 */
public class OrderNodeFieldValueDO extends BaseDO {

    private static final long serialVersionUID = 848767190633057261L;


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
