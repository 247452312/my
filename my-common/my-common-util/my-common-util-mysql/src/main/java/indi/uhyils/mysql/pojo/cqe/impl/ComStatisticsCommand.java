package indi.uhyils.mysql.pojo.cqe.impl;

import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.enums.FieldMarkEnum;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.enums.MysqlCommandTypeEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.pojo.response.impl.ResultSetResponse;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 获取服务器某个信息的请求
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComStatisticsCommand extends AbstractMysqlCommand {

    /**
     * 静态信息表
     */
    private static final String STATIC_TABLE_NAME = "static_info";

    /**
     * 根节点数据库名称
     */
    private String root;

    public ComStatisticsCommand(MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlThisRequestInfo);
        root = SpringUtil.getProperty("mysql.db-name", "root");
    }

    @Override
    public List<MysqlResponse> invoke() {
        ArrayList<FieldInfo> fields = new ArrayList<>();
        MysqlTcpInfo mysqlTcpInfo = MysqlContent.MYSQL_TCP_INFO.get();
        fields.add(new FieldInfo(root, STATIC_TABLE_NAME, STATIC_TABLE_NAME, "运行时间", "time", 3, (int) mysqlTcpInfo.index(), FieldTypeEnum.FIELD_TYPE_LONG, FieldMarkEnum.TIMESTAMP_FLAG
            .getCode(), (byte) 3));

        fields
            .add(new FieldInfo(root, STATIC_TABLE_NAME, STATIC_TABLE_NAME, "每秒执行次数", "executions_per_second", 3, (int) mysqlTcpInfo.index(), FieldTypeEnum.FIELD_TYPE_LONG, FieldMarkEnum.ZEROFILL_FLAG
                .getCode(), (byte) 3));

        List<Map<String, Object>> jsonArrayObj = new ArrayList<>(1);
        Map<String, Object> jsonResult = new HashMap<>(2);
        jsonResult.put("time", 0L);
        jsonResult.put("executions_per_second", 0L);
        jsonArrayObj.add(jsonResult);
        return Arrays
            .asList(new ResultSetResponse(fields, jsonArrayObj, MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS, mysqlTcpInfo.warnCount()));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_STATISTICS;
    }

    @Override
    protected void load() {
    }
}
