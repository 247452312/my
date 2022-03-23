package indi.uhyils.pojo.cqe.impl;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.ErrResponse;
import indi.uhyils.pojo.response.impl.OkResponse;
import indi.uhyils.protocol.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.enums.SqlTypeEnum;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.protocol.mysql.history.decoder.Proto;
import indi.uhyils.util.SpringUtil;
import java.util.Arrays;
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

    public ComInitDbCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlBytes, 4);
        this.sql = proto.get_lenenc_str().trim().toUpperCase(Locale.ROOT);

    }

    @Override
    public List<MysqlResponse> invoke() {
        // use开头
        if (!sql.startsWith(SQL_START)) {
            return Arrays.asList(new ErrResponse(getMysqlHandler(), MysqlErrCodeEnum.EE_UNKNOWN_OPTION, MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS));
        }
        // 数据库名称和标准名称一致
        String dbName = sql.substring(SQL_START.length()).trim();
        String root = SpringUtil.getProperty("mysql.db-name", "root");
        if (Objects.equals(root, dbName)) {
            return Arrays.asList(new OkResponse(getMysqlHandler(), SqlTypeEnum.USE));
        }
        // 不一致就报错
        return Arrays.asList(new ErrResponse(getMysqlHandler(), MysqlErrCodeEnum.EE_UNKNOWN_OPTION, MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS, "没有发现数据库: " + dbName + ",推荐: " + root));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_INIT_DB;
    }

}
