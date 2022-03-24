package indi.uhyils.pojo.cqe.impl;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.enums.MysqlErrCodeEnum;
import indi.uhyils.enums.MysqlHandlerStatusEnum;
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
public class ComQuitCommand extends AbstractMysqlCommand {

    public ComQuitCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
    }

    @Override
    protected void load() {
        // 既然是quit 就没啥好load的了
    }

    @Override
    public List<MysqlResponse> invoke() {
        mysqlTcpInfo.setStatus(MysqlHandlerStatusEnum.OVER);
        return Arrays.asList(new ErrResponse(mysqlTcpInfo,
                                             MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE,
                                             MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS,
                                             "?? 你竟然想关掉我? 脑子瓦特了?\n" + MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE.getMsg()));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_QUIT;
    }
}
