package indi.uhyils.pojo.cqe.impl;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.ErrResponse;
import indi.uhyils.protocol.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;
import java.util.Arrays;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComDropDbCommand extends AbstractMysqlCommand {

    public ComDropDbCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
    }

    @Override
    protected void load() {

    }

    @Override
    public List<MysqlResponse> invoke() {
        return Arrays.asList(new ErrResponse(getMysqlHandler(), MysqlErrCodeEnum.EE_UNKNOWN_OPTION, MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS, "请去对接平台配置页面删除表"));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_DROP_DB;
    }
}
