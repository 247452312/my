package indi.uhyils.mysql.service;

import indi.uhyils.mysql.pojo.DTO.DatabaseInfo;
import indi.uhyils.mysql.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.pojo.cqe.query.BlackQuery;
import java.util.List;

/**
 * mysql这一层需要的service
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月16日 17时19分
 */
public interface MysqlSdkService {

    /**
     * mysql登录
     *
     * @param mysqlCommand
     *
     * @return
     */
    MysqlResponse login(MysqlAuthCommand mysqlCommand);

    /**
     * 获取有权限的数据库列表
     *
     * @param blackQuery
     *
     * @return
     */
    List<DatabaseInfo> getAllDatabaseInfo(BlackQuery blackQuery);
}
