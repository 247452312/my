package indi.uhyils.pojo.entity.type;

import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.util.Asserts;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 15时39分
 */
public class Identifier implements BaseType, Comparable<Identifier> {

    private final Long id;

    public Identifier(Long id) {
        Asserts.assertTrue(id != null, "生成id时 id不能为null");
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

    @Override
    public int compareTo(Identifier o) {
        if (o == null) {
            return -1;
        }
        return id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Identifier that = (Identifier) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
