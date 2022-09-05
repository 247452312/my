package indi.uhyils.mysql.pojo.cqe.impl;

import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.decode.Proto;
import indi.uhyils.mysql.enums.MysqlCommandTypeEnum;
import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.enums.SqlTypeEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.pojo.DTO.DatabaseInfo;
import indi.uhyils.mysql.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.mysql.pojo.response.impl.OkResponse;
import indi.uhyils.mysql.service.MysqlSdkService;
import indi.uhyils.pojo.cqe.query.BlackQuery;
import indi.uhyils.util.SpringUtil;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComInitDbCommand extends AbstractMysqlCommand {

    /**
     * 使用
     */
    private static final String SQL_START = "USE ";

    private String sql;

    private MysqlSdkService mysqlSdkService;

    public ComInitDbCommand(MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlThisRequestInfo);
        this.mysqlSdkService = SpringUtil.getBean(MysqlSdkService.class);
    }

    @Override
    public List<MysqlResponse> invoke() {
        final MysqlTcpInfo mysqlTcpInfo = MysqlContent.MYSQL_TCP_INFO.get();
        // use开头
        if (!sql.startsWith(SQL_START)) {
            return Collections.singletonList(new ErrResponse(MysqlErrCodeEnum.EE_UNKNOWN_OPTION, MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS));
        }
        // 数据库名称和标准名称一致
        String dbName = sql.substring(SQL_START.length()).trim();
        final BlackQuery blackQuery = new BlackQuery();
        if (MysqlContent.SYS_DATABASE.contains(dbName)) {
            mysqlTcpInfo.setDatabase(dbName);
            return Collections.singletonList(new OkResponse(SqlTypeEnum.USE));
        }
        // 获取这个人有权限的数据库列表
        List<DatabaseInfo> databaseInfos = mysqlSdkService.getAllDatabaseInfo(blackQuery);
        if (databaseInfos.stream().anyMatch(t -> Objects.equals(t.getSchemaName(), dbName))) {
            return Collections.singletonList(new OkResponse(SqlTypeEnum.USE));
        }
        // 不一致就报错
        return Collections.singletonList(new ErrResponse(MysqlErrCodeEnum.EE_UNKNOWN_OPTION, MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS, "没有发现数据库: " + dbName));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_INIT_DB;
    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlThisRequestInfo.getMysqlBytes(), 4);
        this.sql = proto.get_lenenc_str().trim().toUpperCase(Locale.ROOT);

    }

}
