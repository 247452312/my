package indi.uhyils.util.mysql.pojo.request.impl;

import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.enums.SqlTypeEnum;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.history.decoder.Proto;
import indi.uhyils.protocol.mysql.history.handler.MysqlHandler;
import indi.uhyils.util.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.util.mysql.pojo.response.MysqlResponse;
import indi.uhyils.util.mysql.pojo.response.impl.OkResponse;
import java.util.Arrays;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComStmtPrepareRequest extends AbstractMysqlRequest {


    /**
     * 临时预处理缓存id
     */
    private long prepareId;

    public ComStmtPrepareRequest(MysqlTcpInfo mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlBytes, 1);
        String sql = proto.get_lenenc_str();
        MysqlHandler mysqlHandler = getMysqlHandler();
        this.prepareId = mysqlHandler.setPrepareSql(sql);
    }

    @Override
    public List<MysqlResponse> invoke() {
        return Arrays.asList(new OkResponse(getMysqlHandler(), SqlTypeEnum.QUERY, 0L, prepareId, MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS, getMysqlHandler().getWarnCount(), "" + prepareId));
    }
}
