package indi.uhyils.service;

import indi.uhyils.pojo.cqe.MysqlCommand;
import indi.uhyils.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.pojo.cqe.impl.MysqlSqlCommand;
import indi.uhyils.pojo.response.MysqlResponse;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月23日 09时04分
 */
public interface PlatformService extends BaseService {

    /**
     * mysql用户登录
     *
     * @param mysqlCommand
     *
     * @return
     */
    MysqlResponse login(MysqlAuthCommand mysqlCommand);

    /**
     * 执行sql语句
     *
     * @param command
     *
     * @return
     */
    List<MysqlResponse> query(MysqlSqlCommand command);

    /**
     * 查询字段信息
     *
     * @param command
     *
     * @return
     */
    List<MysqlResponse> findFieldList(MysqlCommand command);

    /**
     * 获取table信息
     *
     * @param command
     *
     * @return
     */
    List<MysqlResponse> findTableInfo(MysqlCommand command);
}
