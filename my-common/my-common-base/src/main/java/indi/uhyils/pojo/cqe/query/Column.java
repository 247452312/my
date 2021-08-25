package indi.uhyils.pojo.cqe.query;

import indi.uhyils.enum_.OrderSymbolEnum;
import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 18时04分
 */
public class Column implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 列名称
     */
    private ColumnName columnName;

    /**
     * 正序还是倒序
     */
    private OrderSymbolEnum symbol;


    public ColumnName getColumnName() {
        return columnName;
    }

    public void setColumnName(ColumnName columnName) {
        this.columnName = columnName;
    }

    public OrderSymbolEnum getSymbol() {
        return symbol;
    }

    public void setSymbol(OrderSymbolEnum symbol) {
        this.symbol = symbol;
    }
}
