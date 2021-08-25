package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.query.BaseQuery;

/**
 * 删除命令
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 21时07分
 */
public class RemoveCommand implements BaseCommand {

    private BaseQuery order;

    public BaseQuery getOrder() {
        return order;
    }

    public void setOrder(BaseQuery order) {
        this.order = order;
    }
}
