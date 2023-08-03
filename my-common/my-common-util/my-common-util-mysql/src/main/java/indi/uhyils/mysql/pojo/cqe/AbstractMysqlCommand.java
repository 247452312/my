package indi.uhyils.mysql.pojo.cqe;

import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.handler.MysqlTcpInfoObserver;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.command.base.AbstractCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 15时03分
 */
public abstract class AbstractMysqlCommand extends AbstractCommand implements MysqlCommand, MysqlTcpInfoObserver {


    /**
     * 要处理的字节数组
     */
    protected MysqlThisRequestInfo mysqlThisRequestInfo;

    protected AbstractMysqlCommand(MysqlThisRequestInfo mysqlThisRequestInfo) {
        MysqlTcpInfo mysqlTcpInfo = MysqlContent.MYSQL_TCP_INFO.get();
        UserDTO userInfo = mysqlTcpInfo.getUserDTO();
        if (userInfo != null) {
            String token = userInfo.getToken();
            UserInfoHelper.setToken(token);
            UserInfoHelper.setUser(userInfo);
        }
        this.mysqlThisRequestInfo = mysqlThisRequestInfo;
        load();
    }

    @Override
    public MysqlThisRequestInfo getMysqlThisRequestInfo() {
        return mysqlThisRequestInfo;
    }

    /**
     * 加载
     */
    protected abstract void load();
}
