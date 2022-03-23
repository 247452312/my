package indi.uhyils.pojo.cqe.impl;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.enums.SqlTypeEnum;
import indi.uhyils.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.OkResponse;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;
import java.util.Collections;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComPingCommand extends AbstractMysqlCommand {

    public ComPingCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
    }

    @Override
    protected void load() {
        // ping内容. 无需返回值
    }

    @Override
    public List<MysqlResponse> invoke() {
        return Collections
            .singletonList(new OkResponse(mysqlTcpInfo, SqlTypeEnum.NULL));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_PING;
    }
}
