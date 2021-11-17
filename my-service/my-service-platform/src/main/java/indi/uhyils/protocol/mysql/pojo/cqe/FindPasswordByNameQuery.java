package indi.uhyils.protocol.mysql.pojo.cqe;

import indi.uhyils.enum_.Symbol;
import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.cqe.query.base.AbstractArgQuery;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import java.util.Collections;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月17日 18时57分
 */
public class FindPasswordByNameQuery extends AbstractArgQuery {

    private final String name;

    public FindPasswordByNameQuery(String name) {
        this.name = name;
        Arg as = Arg.as(ConsumerInfoDTO::getName, Symbol.EQ, name);
        setArgs(Collections.singletonList(as));
    }


    public String getName() {
        return name;
    }
}
