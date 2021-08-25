package indi.uhyils.pojo.cqe.query;

import indi.uhyils.pojo.DTO.request.model.Arg;
import java.util.Collections;


/**
 * idOrder
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时44分
 */
public class IdQuery extends AbstractQuery {

    private Long id;

    public IdQuery(Long id) {
        super(Collections.singletonList(new Arg("id", "=", id)));
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
