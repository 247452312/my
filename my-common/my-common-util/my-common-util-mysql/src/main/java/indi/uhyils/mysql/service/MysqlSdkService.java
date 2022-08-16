package indi.uhyils.mysql.service;

import indi.uhyils.mysql.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;

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
}
