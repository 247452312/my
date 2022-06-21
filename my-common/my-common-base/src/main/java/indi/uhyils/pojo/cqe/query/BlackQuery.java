package indi.uhyils.pojo.cqe.query;

import indi.uhyils.pojo.cqe.query.base.AbstractArgQuery;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 17时44分
 */
public class BlackQuery extends AbstractArgQuery {

    public BlackQuery() {
        super();
    }

    public BlackQuery(List<Arg> args) {
        super(args);
    }

    public BlackQuery(List<Arg> args, Order order, Limit limit) {
        super(args, order, limit);
    }

    @Override
    public List<Arg> getArgs() {
        return args;
    }

    @Override
    public void setArgs(List<Arg> args) {
        this.args = args;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public Limit getLimit() {
        return limit;
    }

    @Override
    public void setLimit(Limit limit) {
        this.limit = limit;
    }
}
