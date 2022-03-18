package indi.uhyils.pojo.request.impl;

import indi.uhyils.enums.SqlTypeEnum;
import indi.uhyils.handler.MysqlHandler;
import indi.uhyils.pojo.request.AbstractMysqlRequest;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.OkResponse;
import java.util.Collections;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComPingRequest extends AbstractMysqlRequest {

    public ComPingRequest(MysqlHandler mysqlHandler) {
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
