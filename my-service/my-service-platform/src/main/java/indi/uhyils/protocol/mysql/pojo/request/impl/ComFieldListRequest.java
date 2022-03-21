package indi.uhyils.protocol.mysql.pojo.request.impl;

import indi.uhyils.protocol.mysql.decoder.Proto;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComFieldListRequest extends AbstractMysqlRequest {

    private String tableName;

    private List<String> fieldList;

    public ComFieldListRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlBytes, 1);
        this.tableName = proto.get_null_str();
        String lenencStr = proto.get_lenenc_str();
        // todo 测试传过来的是什么

    }

    @Override
    public List<MysqlResponse> invoke() {
        /*1.根据tableName去数据库获取*/
        return null;
    }
}
