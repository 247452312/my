package indi.uhyils.pojo.cqe.query;

import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.base.BaseQuery;
import java.util.List;


/**
 * idOrder
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时44分
 */
public class IdsQuery extends DefaultCQE implements BaseQuery {

    private List<Long> ids;

    public IdsQuery(List<Long> ids) {
        super();
        this.ids = ids;
    }

    public IdsQuery() {
        super();
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

}
