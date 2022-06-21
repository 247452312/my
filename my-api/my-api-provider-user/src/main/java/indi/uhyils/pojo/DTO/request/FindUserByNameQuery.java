package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.cqe.query.base.AbstractQuery;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月17日 18时57分
 */
public class FindUserByNameQuery extends AbstractQuery {

    private final String name;

    public FindUserByNameQuery(String name) {
        this.name = name;
    }

    public static FindUserByNameQuery build(String name) {
        return new FindUserByNameQuery(name);
    }

    public String getName() {
        return name;
    }
}
