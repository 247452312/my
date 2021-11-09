package indi.uhyils.protocol.mysql.pojo.response;

import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.MysqlServerInfo;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.List;

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
        List<byte[]> result = new ArrayList<>();
        byte[] e = toByteNoMarkIndex();
        result.add(MysqlUtil.toBytes(e.length + 1, 1));
        result.add(new byte[3]);
        result.add(new byte[]{getFirstByte()});
        result.add(e);
        return MysqlUtil.mergeListBytes(result);
    }

    protected MysqlHandler getMysqlHandler() {
        return mysqlHandler;
    }

    protected MysqlServerInfo getMysqlServerInfo() {
        return mysqlServerInfo;
    }

}
