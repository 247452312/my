package indi.uhyils.pojo.cqe.query;

import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import java.util.List;

/**
 * 请求
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时45分
 */
public abstract class AbstractArgQuery extends DefaultCQE implements BaseArgQuery {

    protected List<Arg> args;

    protected Order order;

    protected Limit limit;

    public AbstractArgQuery() {
    }

    public AbstractArgQuery(List<Arg> args) {
        this(args, new Order(), new Limit());
    }

    public AbstractArgQuery(List<Arg> args, Order order, Limit limit) {
        this.args = args;
        this.order = order;
        this.limit = limit;
    }

    @Override
    public List<Arg> getArgs() {
        return args;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public Limit getLimit() {
        return limit;
    }
}
