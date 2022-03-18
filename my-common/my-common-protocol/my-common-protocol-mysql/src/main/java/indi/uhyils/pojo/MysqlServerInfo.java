package indi.uhyils.pojo;

import java.io.Serializable;


/**
 * mysql服务器信息
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月06日 11时08分
 */
public class MysqlServerInfo implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 库名
     */
    private String dbName;


    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
