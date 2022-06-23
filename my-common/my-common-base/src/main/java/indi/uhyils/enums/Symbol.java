package indi.uhyils.enums;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.util.Asserts;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 09时06分
 */
public enum Symbol {
    /**
     * 同code
     */
    EQ("="),
    ne("!="),
    gt(">"),
    ge(">="),
    lt("<"),
    le("<="),
    like("like"),
    in("in"),
    is_null("isNull"),
    is_not_null("isNotNull"),
    likeRight("likeRight");

    private String code;

    Symbol(String code) {
        this.code = code;
    }


    public static Optional<Symbol> parse(String code) {
        for (Symbol value : values()) {
            if (Objects.equals(value.getCode(), code)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

    /**
     * 转换args
     *
     * @param args
     * @param <T>
     *
     * @return
     */
    public static <T> QueryWrapper<T> makeWrapper(List<Arg> args) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        for (Arg arg : args) {
            fillWrapper(wrapper, arg);
        }
        return wrapper;
    }

    /**
     * 填充wrapper
     *
     * @param wrapper
     * @param arg
     * @param <T>
     */
    public static <T> void fillWrapper(QueryWrapper<T> wrapper, Arg arg) {
        String symbol = arg.getSymbol();
        Optional<Symbol> parse = Symbol.parse(symbol);

        Asserts.assertTrue(parse.isPresent(), "符号不存在: " + arg.getSymbol());
        switch (parse.get()) {
            case EQ:
                wrapper.eq(arg.getName(), arg.getData());
                return;
            case ge:
                wrapper.ge(arg.getName(), arg.getData());
                return;
            case gt:
                wrapper.gt(arg.getName(), arg.getData());
                return;
            case le:
                wrapper.le(arg.getName(), arg.getData());
                return;
            case lt:
                wrapper.lt(arg.getName(), arg.getData());
                return;
            case ne:
                wrapper.ne(arg.getName(), arg.getData());
                return;
            case in:
                wrapper.in(arg.getName(), arg.getData());
            case like:
                wrapper.like(arg.getName(), arg.getData());
                return;
            case likeRight:
                wrapper.likeRight(arg.getName(), arg.getData());
                return;
            default:
                Asserts.throwException("符号不存在: " + arg.getSymbol());
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
