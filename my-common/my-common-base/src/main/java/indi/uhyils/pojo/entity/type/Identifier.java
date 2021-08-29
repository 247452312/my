package indi.uhyils.pojo.entity.type;

import indi.uhyils.pojo.cqe.query.IdQuery;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 15时39分
 */
public class Identifier implements BaseType {

    private final Long id;

    public Identifier(Long id) {
        this.id = id;
    }

    public Identifier(IdQuery request) {
        this.id = request.getId();
    }

    public static Identifier build(Long id) {
        return new Identifier(id);
    }

    public Long getId() {
        return id;
    }
}