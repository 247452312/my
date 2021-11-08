package indi.uhyils.protocol.mysql.pojo.request.impl;

import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComDebugRequest extends AbstractMysqlRequest {

    public ComDebugRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {

    }

    @Override
    public MysqlResponse invoke() {
        return null;
    }
}
