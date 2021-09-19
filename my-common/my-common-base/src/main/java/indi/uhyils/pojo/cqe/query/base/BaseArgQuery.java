package indi.uhyils.pojo.cqe.query.base;

import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 17时34分
 */
public interface BaseArgQuery extends BaseQuery {

    /**
     * 获取参数
     *
     * @return
     */
    List<Arg> getArgs();


    /**
     * 获取排序
     *
     * @return
     */
    Order getOrder();

    /**
     * 获取分页
     *
     * @return
     */
    Limit getLimit();
}
