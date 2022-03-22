package indi.uhyils.util.mysql.pojo.request;

import indi.uhyils.context.UserContext;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfoObserver;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 15时03分
 */
public abstract class AbstractMysqlRequest implements MysqlRequest, MysqlTcpInfoObserver {

    /**
     * mysql客户端连接
     */
    protected MysqlTcpInfo mysqlTcpInfo;


    /**
     * 要处理的字节数组
     */
    protected MysqlThisRequestInfo mysqlThisRequestInfo;

    protected AbstractMysqlRequest(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        this.mysqlTcpInfo = mysqlTcpInfo;
        UserDTO userInfo = mysqlTcpInfo.getUserDTO();
        this.mysqlThisRequestInfo = mysqlThisRequestInfo;
        if (userInfo != null) {
            String token = userInfo.getToken();
            UserContext.setToken(token);
            UserContext.setUser(userInfo);
        }
    }

    @Override
    public MysqlTcpInfo getMysqlTcpInfo() {
        return mysqlTcpInfo;
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
