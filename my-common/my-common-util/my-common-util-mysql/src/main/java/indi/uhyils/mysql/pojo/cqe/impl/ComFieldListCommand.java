package indi.uhyils.mysql.pojo.cqe.impl;

import indi.uhyils.mysql.decode.Proto;
import indi.uhyils.mysql.enums.MysqlCommandTypeEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComFieldListCommand extends AbstractMysqlCommand {

    private String tableName;

    private List<String> fieldList;

    public ComFieldListCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
    }

    @Override
    public List<MysqlResponse> invoke() {
        /*1.根据tableName去数据库获取*/
        return null;
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_FIELD_LIST;
    }

    @Override
    protected void load() {
        Proto proto = new Proto(getMysqlThisRequestInfo().getMysqlBytes(), 1);
        this.tableName = proto.get_null_str();
        String lenencStr = proto.get_lenenc_str();
        // todo 测试传过来的是什么

    }
}
