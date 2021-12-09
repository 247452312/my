package indi.uhyils.pojo.cqe.query;

import indi.uhyils.enum_.Symbol;
import indi.uhyils.pojo.cqe.query.base.AbstractArgQuery;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import java.util.Collections;


/**
 * idOrder
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时44分
 */
public class IdQuery extends AbstractArgQuery {

    private Long id;

    public IdQuery(Long id) {
        super(Collections.singletonList(Arg.as(IdQuery::getId, Symbol.EQ, id)));
        this.id = id;
    }
    public IdQuery() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
