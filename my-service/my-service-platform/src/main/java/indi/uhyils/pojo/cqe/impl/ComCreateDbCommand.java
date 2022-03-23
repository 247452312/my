package indi.uhyils.pojo.cqe.impl;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.enums.MysqlErrCodeEnum;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.ErrResponse;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;
import java.util.Arrays;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComCreateDbCommand extends AbstractMysqlCommand {

    public ComCreateDbCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
    }

    @Override
    protected void load() {

    }

    @Override
    public List<MysqlResponse> invoke() {
        return Arrays.asList(new ErrResponse(mysqlTcpInfo, MysqlErrCodeEnum.EE_UNKNOWN_OPTION, MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS, "请去对接平台配置页面建表"));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_CREATE_DB;
    }
}
