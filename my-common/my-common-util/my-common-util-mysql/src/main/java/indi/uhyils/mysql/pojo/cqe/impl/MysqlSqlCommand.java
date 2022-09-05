package indi.uhyils.mysql.pojo.cqe.impl;


import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.pojo.cqe.AbstractMysqlCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月24日 12时38分
 */
public abstract class MysqlSqlCommand extends AbstractMysqlCommand {

    /**
     * 对应的sql语句
     */
    protected String sql;

    protected MysqlSqlCommand(MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlThisRequestInfo);
    }

    public String getSql() {
        return sql;
    }

}
