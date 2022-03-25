package indi.uhyils.pojo.cqe.impl;

import com.alibaba.fastjson.JSON;
import indi.uhyils.enum_.FieldMarkEnum;
import indi.uhyils.enum_.FieldTypeEnum;
import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.pojo.DTO.FieldInfo;
import indi.uhyils.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.ResultSetResponse;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.util.SpringUtil;
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

    private String root;

    public ComStatisticsCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
        root = SpringUtil.getProperty("mysql.db-name", "root");
    }

    @Override
    protected void load() {
    }

    @Override
    public List<MysqlResponse> invoke() {
        ArrayList<FieldInfo> fields = new ArrayList<>();
        fields.add(new FieldInfo(root, STATIC_TABLE_NAME, STATIC_TABLE_NAME, "运行时间", "time", 3, index, FieldTypeEnum.FIELD_TYPE_LONG, FieldMarkEnum.TIMESTAMP_FLAG
            .getCode(), (byte) 3, null, planIndex));

        fields
            .add(new FieldInfo(root, STATIC_TABLE_NAME, STATIC_TABLE_NAME, "每秒执行次数", "executions_per_second", 3, index, FieldTypeEnum.FIELD_TYPE_LONG, FieldMarkEnum.ZEROFILL_FLAG
                .getCode(), (byte) 3, null, planIndex));

        List<Map<String, Object>> jsonArrayObj = new ArrayList<>(1);
        Map<String, Object> jsonResult = new HashMap<>(2);
        jsonResult.put("time", 0L);
        jsonResult.put("executions_per_second", 0L);
        jsonArrayObj.add(jsonResult);
        return Arrays
            .asList(new ResultSetResponse(mysqlTcpInfo, fields, JSON.parseArray(JSON.toJSONString(jsonArrayObj)), MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS, mysqlTcpInfo.warnCount()));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_STATISTICS;
    }
}
