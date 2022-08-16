package indi.uhyils.mysql.pojo.cqe.impl;

import indi.uhyils.mysql.decode.Proto;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.enums.MysqlCommandTypeEnum;
import indi.uhyils.mysql.enums.PrepareMarkEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.handler.PrepareInfo;
import indi.uhyils.mysql.pojo.DTO.PrepareParamInfo;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.util.MysqlUtil;
import java.util.ArrayList;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComStmtExecuteCommand extends MysqlSqlCommand {

    /**
     * 预处理语句
     */
    private PrepareInfo prepareSql;

    /**
     * 处理完成的完整sql
     */
    private String sql;

    /**
     * 预处理语句占位符index
     */
    private List<Integer> placeholderIndexs;


    /**
     * 预处理语句标志位
     */
    private PrepareMarkEnum parse;

    public ComStmtExecuteCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
    }

    @Override
    public List<MysqlResponse> invoke() throws Exception {
        ComQueryCommand comQueryRequest = new ComQueryCommand(mysqlTcpInfo, mysqlThisRequestInfo, sql);
        return comQueryRequest.invoke();
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_STMT_EXECUTE;
    }

    @Override
    protected void load() {
        byte[] mysqlBytes = mysqlThisRequestInfo.getMysqlBytes();
        Proto proto = new Proto(mysqlBytes, 1);
        long prepareId = proto.getFixedInt(4);
        // 预处理语句
        this.prepareSql = mysqlTcpInfo.getPrepareSql(prepareId);
        long fixedInt = proto.getFixedInt(1);
        byte[] bytes = MysqlUtil.toBytes(fixedInt);
        // 预处理语句标志位
        this.parse = PrepareMarkEnum.parse(bytes[0]);
        proto.get_filler(4);
        Integer count = prepareSql.getCount();
        if (count != 0) {
            // null占位符
            int nullBit = (count + 7) / 8;
            proto.get_filler(nullBit);
            // 参数分割标志
            proto.get_filler(1);
            List<PrepareParamInfo> params = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                long paramType = proto.getFixedInt(2);
                FieldTypeEnum parse = FieldTypeEnum.parse((byte) paramType);
                Object paramValue = parse.invokeProto(proto);
                PrepareParamInfo prepareParamInfo = new PrepareParamInfo(parse, paramValue);
                params.add(prepareParamInfo);
            }
            this.sql = prepareSql.replacePrepareSql(params);
        }
    }
}
