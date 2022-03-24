package indi.uhyils.pojo.cqe.impl;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.enums.SqlTypeEnum;
import indi.uhyils.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.OkResponse;
import indi.uhyils.protocol.mysql.decode.Proto;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.protocol.mysql.handler.PrepareInfo;
import java.util.Arrays;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComStmtPrepareCommand extends AbstractMysqlCommand {


    /**
     * 临时预处理缓存id
     */
    private long prepareId;

    public ComStmtPrepareCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
    }

    @Override
    protected void load() {
        byte[] mysqlBytes = mysqlThisRequestInfo.getMysqlBytes();
        Proto proto = new Proto(mysqlBytes, 1);
        String sql = proto.get_lenenc_str();
        this.prepareId = mysqlTcpInfo.addPrepareSql(new PrepareInfo(sql));
    }

    @Override
    public List<MysqlResponse> invoke() {
        return Arrays.asList(new OkResponse(mysqlTcpInfo, SqlTypeEnum.QUERY, 0L, prepareId, MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS, mysqlTcpInfo.warnCount(), "" + prepareId));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_STMT_PREPARE;
    }

}
