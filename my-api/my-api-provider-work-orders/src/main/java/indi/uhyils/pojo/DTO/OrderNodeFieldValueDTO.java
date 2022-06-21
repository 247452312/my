package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分28秒
 */
public class OrderNodeFieldValueDTO extends IdDTO {

    private static final long serialVersionUID = -21095876389861092L;


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
