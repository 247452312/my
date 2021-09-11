package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时46分08秒
 */
@TableName(value = "sys_order_node_field_value")
public class OrderNodeFieldValueDO extends BaseDO {

    private static final long serialVersionUID = -54256949049460778L;


    /**
     * 对应节点属性的id
     */
    @TableField
    private Long nodeFieldId;

    /**
     * 真实值
     */
    @TableField
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
