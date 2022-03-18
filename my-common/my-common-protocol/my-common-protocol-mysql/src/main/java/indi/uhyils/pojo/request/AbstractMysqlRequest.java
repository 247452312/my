package indi.uhyils.pojo.request;

import indi.uhyils.context.UserContext;
import indi.uhyils.handler.MysqlHandler;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.MysqlHandlerObserver;
import indi.uhyils.pojo.MysqlServerInfo;
import indi.uhyils.pojo.MysqlServerObserver;
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
        UserDTO userInfo = mysqlHandler.getUserDTO();
        if (userInfo != null) {
            String token = userInfo.getToken();
            UserContext.setToken(token);
            UserContext.setUser(userInfo);
        }
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
