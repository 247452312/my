package indi.uhyils.builder;

import indi.uhyils.pojo.model.OrderNodeFieldValueEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月15日 16时26分
 */
public class OrderNodeFieldValueBuilder {

    /**
     * 创建一个工单节点属性的真实值
     *
     * @param orderFieldId 工单节点属性的id
     * @param value        用户填写的值
     * @return
     */
    public static OrderNodeFieldValueEntity buildOrderNodeFieldValue(Long orderFieldId, String value) {
        OrderNodeFieldValueEntity fieldValue = new OrderNodeFieldValueEntity();
        fieldValue.setNodeFieldId(orderFieldId);
        fieldValue.setRealValue(value);
        return fieldValue;
    }

    /**
     * 创建很多个工单节点属性的真实值
     *
     * @param map [工单节点属性的id,用户填写的值]
     * @return
     */
    public static List<OrderNodeFieldValueEntity> buildOrderNodeFieldValues(Map<Long, String> map) {
        List<OrderNodeFieldValueEntity> result = new ArrayList<>(map.size());
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            result.add(buildOrderNodeFieldValue(entry.getKey(), entry.getValue()));
        }
        return result;

    }
}
