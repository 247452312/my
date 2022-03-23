package indi.uhyils.service;

import indi.uhyils.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.pojo.response.MysqlResponse;

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

}
