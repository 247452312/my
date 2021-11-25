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

    /**
     * client发过来的index
     */
    protected Integer index;

    protected AbstractMysqlResponse(MysqlHandler mysqlHandler) {
        mysqlServerInfo = SpringUtil.getBean(MysqlServerInfo.class);
        this.mysqlHandler = mysqlHandler;
    }

    @Override
    public List<byte[]> toByte() {
        List<byte[]> bytes = toByteNoMarkIndex();
        List<byte[]> result = new ArrayList<>(bytes.size());
        for (byte[] aByte : bytes) {
            List<byte[]> aByteList = new ArrayList<>();
            aByteList.add(MysqlUtil.toBytes(aByte.length + 1, 1));
            aByteList.add(new byte[2]);
            long realResponseIndex = mysqlHandler.index() + 1;
            aByteList.add(new byte[]{(byte) realResponseIndex});
            mysqlHandler.changeIndex(realResponseIndex);
            aByteList.add(new byte[]{getFirstByte()});
            aByteList.add(aByte);
            result.add(MysqlUtil.mergeListBytes(aByteList));
        }
        return result;

    }

    protected MysqlHandler getMysqlHandler() {
        return mysqlHandler;
    }

    protected MysqlServerInfo getMysqlServerInfo() {
        return mysqlServerInfo;
    }

}
