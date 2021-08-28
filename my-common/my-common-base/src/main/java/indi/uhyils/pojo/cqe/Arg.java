package indi.uhyils.pojo.cqe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义查询中的一个参数
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时57分
 */
public class Arg implements Serializable {

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

    public Arg() {
    }

    public static List<Arg> buildSingleArgs(String name, String symbol, String data) {
        List<Arg> list = new ArrayList<>();
        Arg arg = new Arg();
        arg.setName(name);
        arg.setSymbol(symbol);
        arg.setData(data);
        list.add(arg);
        return list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
