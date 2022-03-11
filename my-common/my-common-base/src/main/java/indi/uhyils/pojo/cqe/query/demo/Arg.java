package indi.uhyils.pojo.cqe.query.demo;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import indi.uhyils.enum_.Symbol;
import indi.uhyils.util.ReflactUtil;
import java.io.Serializable;

/**
 * 自定义查询中的一个参数
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时57分
 */
public class Arg implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 符号
     */
    private String symbol;

    /**
     * 数据
     */
    private Object data;

    public Arg(String name, String symbol, Object data) {
        this.name = name;
        this.symbol = symbol;
        this.data = data;
    }

    public <T> Arg(SFunction<T, ?> name, String symbol, Object data) {
        this.name = ReflactUtil.transSFunction(name);
        this.symbol = symbol;
        this.data = data;
    }

    public Arg() {
    }

    /**
     * 创建一个arg
     *
     * @param name
     * @param symbol
     * @param data
     *
     * @return
     */
    public static Arg as(String name, String symbol, Object data) {
        Arg arg = new Arg();
        arg.setName(name);
        arg.setSymbol(symbol);
        arg.setData(data);
        return arg;
    }

    /**
     * 创建一个arg
     *
     * @param name
     * @param symbol
     * @param data
     *
     * @return
     */
    public static <T> Arg as(SFunction<T, ?> name, Symbol symbol, Object data) {
        Arg arg = new Arg();
        arg.setFunctionName(name);
        arg.setSymbol(symbol.getCode());
        arg.setData(data);
        return arg;
    }

    /**
     * 创建一个arg
     *
     * @param name
     * @param symbol
     * @param data
     *
     * @return
     */
    public static Arg as(String name, Symbol symbol, Object data) {
        Arg arg = new Arg();
        arg.setName(name);
        arg.setSymbol(symbol.getCode());
        arg.setData(data);
        return arg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public <T> void setFunctionName(SFunction<T, ?> name) {
        this.name = ReflactUtil.transSFunction(name);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
