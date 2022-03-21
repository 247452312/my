package indi.uhyils.protocol.mysql.pojo;


import indi.uhyils.protocol.mysql.handler.MysqlHandler;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月05日 21时49分
 */
public interface MysqlHandlerObserver {

    /**
     * 获取mysqlHandler
     *
     * @return
     */
    MysqlHandler getMysqlHandler();

}
