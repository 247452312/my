package indi.uhyils.util;

import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.DO.DbInfoDO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月17日 13时09分
 */
public final class DBManager {

    /**
     * 连接
     *
     * @return
     */
    public static Connection connect(String uniqueId, DbInfoDO dbInfoDO) throws SQLException {
        DbTypeEnum paras = DbTypeEnum.parse(dbInfoDO.getType());
        switch (paras) {
            case MYSQL:

            case ORACLE:
            default:
                throw new SQLException("不支持的连接类型");
        }

    }
}
