package indi.uhyils.util.mysql.pojo.request.impl;

import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.util.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.util.mysql.pojo.response.MysqlResponse;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComTimeRequest extends AbstractMysqlRequest {

    public ComTimeRequest(MysqlTcpInfo mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {

    }

    @Override
    public List<MysqlResponse> invoke() {
        return null;
    }
}
