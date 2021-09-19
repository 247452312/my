package indi.uhyils.pojo.entity;

import indi.uhyils.context.MyContext;
import indi.uhyils.enum_.OrderNodeFieldValueTypeEnum;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.util.AssertUtil;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分27秒
 */
public class OrderNodeFieldValue extends AbstractDoEntity<OrderNodeFieldValueDO> {

    private OrderNodeField field;

    public OrderNodeFieldValue(OrderNodeFieldValueDO dO) {
        super(dO);
    }

    public OrderNodeFieldValue(Long id) {
        super(id, new OrderNodeFieldValueDO());
    }

    public OrderNodeFieldValue(OrderNodeField field, String realValue) {
        super(parseToNodeFieldValue(field, realValue));
        this.field = field;
    }

    private static OrderNodeFieldValueDO parseToNodeFieldValue(OrderNodeField field, String realValue) {
        OrderNodeFieldValueDO orderNodeFieldValueDO = new OrderNodeFieldValueDO();
        orderNodeFieldValueDO.setNodeFieldId(field.id.getId());
        orderNodeFieldValueDO.setRealValue(realValue);
        return orderNodeFieldValueDO;
    }

    public void assertSelf() {
        AssertUtil.assertTrue(field != null, "属性不能为空");
        String realValue = data.getRealValue();

        OrderNodeFieldValueTypeEnum parse = OrderNodeFieldValueTypeEnum.parse(field.toDo().getValueType());
        switch (parse) {
            case DATE:
                AssertUtil.assertTrue(realValue.matches(MyContext.DATE_REGEX), "类型错误,应该为日期类型:" + field.toDo().getName());
                break;
            case EMAIL:
                AssertUtil.assertTrue(realValue.matches(MyContext.EMAIL_REGEX), "类型错误,应该为email类型:" + field.toDo().getName());
                break;
            case VALUE:
                AssertUtil.assertTrue(realValue.matches(MyContext.VALUE_REGEX), "类型错误,应该为数字类型:" + field.toDo().getName());
                break;
            case STRING:
                break;
            case ENGLISH:
                AssertUtil.assertTrue(realValue.matches(MyContext.ENGLISH_REGEX), "类型错误,应该为英文类型:" + field.toDo().getName());
                break;
            default:
                AssertUtil.assertTrue(false, "类型错误,找不到指定类型:" + field.toDo().getName());
                break;

        }
    }
}
