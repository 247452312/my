package indi.uhyils.pojo.cqe.query;

import indi.uhyils.pojo.cqe.query.demo.Arg;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * idOrder
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时44分
 */
public class IdsQuery extends AbstractArgQuery {

    public IdsQuery(List<Long> ids) {
        super(Collections.singletonList(new Arg("id", "in", ids.stream().map(t -> Long.toString(t)).collect(Collectors.joining(",")))));
    }

}
