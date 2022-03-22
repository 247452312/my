package indi.uhyils.util.mysql.pojo.request.impl;

import indi.uhyils.protocol.mysql.enums.SqlTypeEnum;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.util.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.util.mysql.pojo.response.MysqlResponse;
import indi.uhyils.util.mysql.pojo.response.impl.OkResponse;
import java.util.Collections;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComPingRequest extends AbstractMysqlRequest {

    public ComPingRequest(MysqlTcpInfo mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {
        // ping内容. 无需返回值
    }

    @Override
    public List<MysqlResponse> invoke() {
        return Collections
            .singletonList(new OkResponse(getMysqlHandler(), SqlTypeEnum.NULL));
    }
}
