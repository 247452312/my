package indi.uhyils.protocol.mysql.pojo.response;

import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.MysqlServerInfo;
import indi.uhyils.util.SpringUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 08时20分
 */
public abstract class AbstractMysqlResponse implements MysqlResponse {

    /**
     * mysql客户端连接
     */
    protected MysqlHandler mysqlHandler;

    /**
     * mysql服务器信息
     */
    protected MysqlServerInfo mysqlServerInfo;

    protected AbstractMysqlResponse(MysqlHandler mysqlHandler) {
        mysqlServerInfo = SpringUtil.getBean(MysqlServerInfo.class);
        this.mysqlHandler = mysqlHandler;
    }
    @Override
    public byte[] toByte() {
        byte[] bytes = toByteNoMarkIndex();
        byte[] result = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0, result, 1, bytes.length);
        result[0] = getFirstByte();
        return result;
    }

    protected MysqlHandler getMysqlHandler() {
        return mysqlHandler;
    }

    protected MysqlServerInfo getMysqlServerInfo() {
        return mysqlServerInfo;
    }

}
