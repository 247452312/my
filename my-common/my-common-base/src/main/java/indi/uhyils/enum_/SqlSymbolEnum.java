package indi.uhyils.enum_;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月27日 15时17分
 */
public enum SqlSymbolEnum {
    /**
     * 具体见name
     */
    EQ("="),
    NE("!="),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    BETWEEN("between"),
    NOT_BETWEEN("not between"),
    LIKE("like"),
    NOT_LIKE("not like"),
    LIKE_LEFT("like left"),
    LIKE_RIGHT("like right"),
    IS_NULL("is null"),
    IS_NOT_NULL("is not null"),
    IN("in"),
    NOT_IN("not in"),
    IN_SQL("in sql"),
    NOT_IN_SQL("not in sql"),
    GROUP_BY("group by"),
    ORDER_BY("order by"),
    ORDER_BY_ASC("order by asc"),
    ORDER_BY_DESC("order by desc"),
    HAVING("having"),
    OR("or"),
    EXISTS("exists"),
    NOT_EXISTS("not exists");

    private String name;

    SqlSymbolEnum(String name) {
        this.name = name;
    }

    public static SqlSymbolEnum parse(String name) {
        SqlSymbolEnum[] values = SqlSymbolEnum.values();
        for (SqlSymbolEnum value : values) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * mybatis-plus,查询语句
     *
     * @param queryWrapper
     * @param name
     * @param data
     * @param <T>
     */
    public <T extends BaseVoEntity> void excute(QueryWrapper<T> queryWrapper, String name, Object data) {
        switch (this) {
            case EQ:
                queryWrapper.eq(name, data);
                return;
            case GE:
                queryWrapper.ge(name, data);
                return;
            case GT:
                queryWrapper.gt(name, data);
                return;
            case IN:
                queryWrapper.in(name, data);
                return;
            case LE:
                queryWrapper.le(name, data);
                return;
            case LT:
                queryWrapper.lt(name, data);
                return;
            case NE:
                queryWrapper.ne(name, data);
                return;
            case LIKE:
                queryWrapper.like(name, data);
                return;
            case IN_SQL:
                queryWrapper.inSql(name, data.toString());
                return;
            case NOT_IN:
                queryWrapper.notIn(name, data);
                return;
            case IS_NULL:
                queryWrapper.isNull(name);
                return;
            case NOT_LIKE:
                queryWrapper.notLike(name, data);
                return;
            case ORDER_BY:
                queryWrapper.orderBy(true, true, name);
                return;
            case LIKE_LEFT:
                queryWrapper.likeLeft(name, data);
                return;
            case LIKE_RIGHT:
                queryWrapper.likeRight(name, data);
                return;
            case NOT_IN_SQL:
                queryWrapper.notInSql(name, data.toString());
                return;
            case IS_NOT_NULL:
                queryWrapper.isNotNull(name);
                return;
            case ORDER_BY_ASC:
                queryWrapper.orderByAsc(name);
                return;
            case ORDER_BY_DESC:
                queryWrapper.orderByDesc(name);
                return;
            default:
                return;
        }
    }
}
