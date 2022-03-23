package indi.uhyils.pojo.cqe.impl;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComDelayedInsertCommand extends AbstractMysqlCommand {

    public ComDelayedInsertCommand(MysqlTcpInfo mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {

    }

    @Override
    public List<MysqlResponse> invoke() {
        return null;
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_DELAYED_INSERT;
    }
}
