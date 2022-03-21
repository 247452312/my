package indi.uhyils.protocol.mysql.pojo.request.impl;

import indi.uhyils.protocol.mysql.enums.MysqlRefreshEnum;
import indi.uhyils.protocol.mysql.enums.SqlTypeEnum;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.OkResponse;
import java.util.Arrays;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComRefreshRequest extends AbstractMysqlRequest {

    private MysqlRefreshEnum refresh;

    public ComRefreshRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }


    @Override
    protected void load() {
        byte mysqlByte = mysqlBytes[1];
        this.refresh = MysqlRefreshEnum.parse(mysqlByte);
    }

    @Override
    public List<MysqlResponse> invoke() {
        return Arrays.asList(new OkResponse(getMysqlHandler(), SqlTypeEnum.DELETE));
    }
}
