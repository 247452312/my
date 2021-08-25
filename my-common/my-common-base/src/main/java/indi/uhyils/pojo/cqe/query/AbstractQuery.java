package indi.uhyils.pojo.cqe.query;

import indi.uhyils.pojo.DTO.request.model.Arg;
import java.util.List;

/**
 * 请求
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时45分
 */
public abstract class AbstractQuery implements BaseQuery {

    private final List<Arg> args;

    private final Order order;

    private final Limit limit;

    public AbstractQuery(List<Arg> args) {
        this(args, new Order(), new Limit());
    }

    public AbstractQuery(List<Arg> args, Order order, Limit limit) {
        this.args = args;
        this.order = order;
        this.limit = limit;
    }

    @Override
    public List<Arg> args() {
        return args;
    }

    @Override
    public Order order() {
        return order;
    }

    @Override
    public Limit limit() {
        return limit;
    }
}
