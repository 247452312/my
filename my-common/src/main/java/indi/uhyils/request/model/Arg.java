package indi.uhyils.request.model;

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
