package indi.uhyils.protocol.mysql.pojo.request;

import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.MysqlHandlerObserver;
import indi.uhyils.protocol.mysql.pojo.MysqlServerInfo;
import indi.uhyils.protocol.mysql.pojo.MysqlServerObserver;
import indi.uhyils.util.SpringUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 15时03分
 */
public abstract class AbstractMysqlRequest implements MysqlRequest, MysqlHandlerObserver, MysqlServerObserver {

    /**
     * mysql客户端连接
     */
    protected MysqlHandler mysqlHandler;

    /**
     * mysql服务器信息
     */
    protected MysqlServerInfo mysqlServerInfo;

    /**
     * 要处理的字节数组
     */
    protected byte[] mysqlBytes;

    protected AbstractMysqlRequest(MysqlHandler mysqlHandler) {
        mysqlServerInfo = SpringUtil.getBean(MysqlServerInfo.class);
        this.mysqlHandler = mysqlHandler;
    }

    @Override
    public MysqlHandler getMysqlHandler() {
        return mysqlHandler;
    }

    @Override
    public MysqlServerInfo getMysqlServerInfo() {
        return mysqlServerInfo;
    }

    @Override
    public void load(byte[] mysqlBytes) {
        this.mysqlBytes = mysqlBytes;
        load();
    }

    /**
     * 加载
     */
    protected abstract void load();
}
