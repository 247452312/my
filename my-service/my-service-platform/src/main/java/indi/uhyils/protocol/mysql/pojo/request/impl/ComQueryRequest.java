package indi.uhyils.protocol.mysql.pojo.request.impl;

import indi.uhyils.protocol.mysql.decoder.impl.Proto;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComQueryRequest extends AbstractMysqlRequest {

    private String sql;

    public ComQueryRequest(MysqlHandler mysqlHandler, String sql) {
        super(mysqlHandler);
        this.sql = sql;
    }

    public ComQueryRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlBytes, 4);
        this.sql = proto.get_lenenc_str();
    }

    @Override
    public MysqlResponse invoke() {
        // todo 解析query 的sql语句
        return null;
    }
}
