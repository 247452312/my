package indi.uhyils.pojo.cqe.impl;

import com.alibaba.fastjson.JSON;
import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.pojo.DTO.FieldInfo;
import indi.uhyils.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.ResultSetResponse;
import indi.uhyils.protocol.mysql.enums.FieldMarkEnum;
import indi.uhyils.protocol.mysql.enums.FieldTypeEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.history.handler.MysqlHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComStatisticsCommand extends AbstractMysqlCommand {

    /**
     * 静态信息表
     */
    private static final String STATIC_TABLE_NAME = "static_info";

    public ComStatisticsCommand(MysqlTcpInfo mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {
    }

    @Override
    public List<MysqlResponse> invoke() {
        ArrayList<FieldInfo> fields = new ArrayList<>();
        fields.add(new FieldInfo(getMysqlServerInfo().getDbName(), STATIC_TABLE_NAME, STATIC_TABLE_NAME, "运行时间", "time", 3, FieldTypeEnum.FIELD_TYPE_LONG, FieldMarkEnum.TIMESTAMP_FLAG
            .getCode(), (byte) 3, null));

        fields
            .add(new FieldInfo(getMysqlServerInfo().getDbName(), STATIC_TABLE_NAME, STATIC_TABLE_NAME, "每秒执行次数", "executions_per_second", 3, FieldTypeEnum.FIELD_TYPE_LONG, FieldMarkEnum.ZEROFILL_FLAG
                .getCode(), (byte) 3, null));

        List<Map<String, Object>> jsonArrayObj = new ArrayList<>(1);
        Map<String, Object> jsonResult = new HashMap<>(2);
        jsonResult.put("time", 0L);
        jsonResult.put("executions_per_second", 0L);
        jsonArrayObj.add(jsonResult);
        MysqlHandler mysqlHandler = getMysqlHandler();
        return Arrays
            .asList(new ResultSetResponse(getMysqlHandler(), fields, JSON.parseArray(JSON.toJSONString(jsonArrayObj)), MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS, mysqlHandler.getWarnCount()));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_STATISTICS;
    }
}
