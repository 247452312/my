package indi.uhyils.protocol.mysql.pojo.request.impl;

import indi.uhyils.protocol.mysql.decoder.impl.Proto;
import indi.uhyils.protocol.mysql.enums.FieldTypeEnum;
import indi.uhyils.protocol.mysql.enums.PrepareMarkEnum;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.entity.PrepareInfo;
import indi.uhyils.protocol.mysql.pojo.entity.PrepareParamInfo;
import indi.uhyils.protocol.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import java.util.ArrayList;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComStmtExecuteRequest extends AbstractMysqlRequest {

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

    public ComStmtExecuteRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlBytes, 1);
        long prepareId = proto.getFixedInt(4);
        // 预处理语句
        this.prepareSql = getMysqlHandler().getPrepareSql(prepareId);
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

    @Override
    public List<MysqlResponse> invoke() throws Exception {
        ComQueryRequest comQueryRequest = new ComQueryRequest(getMysqlHandler(), sql);
        return comQueryRequest.invoke();
    }
}
