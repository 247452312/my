package indi.uhyils.pojo.cqe.impl;

import indi.uhyils.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月24日 12时38分
 */
public abstract class MysqlSqlCommand extends AbstractMysqlCommand {

    /**
     * 对应的sql语句
     */
    protected String sql;

    protected MysqlSqlCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
    }

    public String getSql() {
        return sql;
    }

}
