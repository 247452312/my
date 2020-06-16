package indi.uhyils.pojo.request.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义查询中的一个参数
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时57分
 */
public class Arg {

    /**
     * 字段名称
     */
    public String name;

    /**
     * 符号
     */
    public String symbol;

    /**
     * 数据
     */
    public Object data;

    public Arg(String name, String symbol, Object data) {
        this.name = name;
        this.symbol = symbol;
        this.data = data;
    }

    public Arg() {
    }

    public static List<Arg> buildSingleArgs(String name, String symbol, String data) {
        List<Arg> list = new ArrayList<>();
        Arg e = new Arg();
        e.setName(name);
        e.setSymbol("=");
        e.setData(data);
        list.add(e);
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
