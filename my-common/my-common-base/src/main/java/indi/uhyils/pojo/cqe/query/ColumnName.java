package indi.uhyils.pojo.cqe.query;

import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 18时03分
 */
public class ColumnName implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 名称
     */
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
