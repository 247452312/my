package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.query.BaseArgQuery;

/**
 * 删除命令
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 21时07分
 */
public class RemoveCommand extends AbstractCommand {

    private BaseArgQuery order;

    public BaseArgQuery getOrder() {
        return order;
    }

    public void setOrder(BaseArgQuery order) {
        this.order = order;
    }
}
