package indi.uhyils.pojo.response;

import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfoObserver;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 08时20分
 */
public abstract class AbstractMysqlResponse implements MysqlResponse, MysqlTcpInfoObserver {

    /**
     * 此次连接的信息
     */
    protected MysqlTcpInfo mysqlTcpInfo;

    protected AbstractMysqlResponse(MysqlTcpInfo mysqlTcpInfo) {
        this.mysqlTcpInfo = mysqlTcpInfo;

    }

    @Override
    public List<byte[]> toByte() {
        List<byte[]> bytes = toByteNoMarkIndex();
        List<byte[]> result = new ArrayList<>(bytes.size());
        for (byte[] aByte : bytes) {
            List<byte[]> aByteList = new ArrayList<>();
            aByteList.add(MysqlUtil.toBytes(aByte.length + 1L, 1));
            aByteList.add(new byte[2]);
            long realResponseIndex = mysqlTcpInfo.index() + 1;
            aByteList.add(new byte[]{(byte) realResponseIndex});
            aByteList.add(new byte[]{getFirstByte()});
            aByteList.add(aByte);
            result.add(MysqlUtil.mergeListBytes(aByteList));
        }
        return result;

    }

    @Override
    public MysqlTcpInfo getMysqlTcpInfo() {
        return mysqlTcpInfo;
    }
}
