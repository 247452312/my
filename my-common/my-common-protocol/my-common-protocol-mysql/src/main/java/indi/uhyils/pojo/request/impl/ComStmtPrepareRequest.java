package indi.uhyils.pojo.request.impl;

import indi.uhyils.decoder.Proto;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.enums.SqlTypeEnum;
import indi.uhyils.handler.MysqlHandler;
import indi.uhyils.pojo.request.AbstractMysqlRequest;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.OkResponse;
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

    public ComStmtPrepareRequest(MysqlHandler mysqlHandler) {
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
