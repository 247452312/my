package indi.uhyils.mysql.pojo.cqe.impl;

import indi.uhyils.mysql.enums.MysqlCommandTypeEnum;
import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.pojo.response.impl.ErrResponse;
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
    public List<MysqlResponse> invoke() {
        return Arrays.asList(new ErrResponse(mysqlTcpInfo, MysqlErrCodeEnum.EE_UNKNOWN_OPTION, MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS, "请去对接平台配置页面删除表"));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_DROP_DB;
    }

    @Override
    protected void load() {

    }
}