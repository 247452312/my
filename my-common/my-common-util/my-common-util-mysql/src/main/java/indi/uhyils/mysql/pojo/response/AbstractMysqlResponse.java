package indi.uhyils.mysql.pojo.response;

import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.enums.MysqlHandlerStatusEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.handler.MysqlTcpInfoObserver;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.util.MysqlUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 08时20分
 */
public abstract class AbstractMysqlResponse implements MysqlResponse, MysqlTcpInfoObserver {

    /**
     * 此次tcp的信息
     */
    protected MysqlTcpInfo mysqlTcpInfo;

    /**
     * 此次请求的信息
     */
    protected MysqlThisRequestInfo mysqlThisRequestInfo;

    protected AbstractMysqlResponse() {
        this.mysqlTcpInfo = MysqlContent.MYSQL_TCP_INFO.get();
    }


    @Override
    public List<byte[]> toByte() {
        MysqlHandlerStatusEnum status = mysqlTcpInfo.getStatus();
        boolean b = status == MysqlHandlerStatusEnum.FIRST_SIGHT || status == MysqlHandlerStatusEnum.UNKNOW;

        List<byte[]> bytes = toByteNoMarkIndex();
        List<byte[]> result = new ArrayList<>(bytes.size());
        for (byte[] aByte : bytes) {
            List<byte[]> aByteList = new ArrayList<>();
            aByteList.add(MysqlUtil.toBytes(aByte.length + (b ? 1 : 0), 1));
            aByteList.add(new byte[2]);
            long realResponseIndex = mysqlTcpInfo.index() + 1;
            aByteList.add(new byte[]{(byte) realResponseIndex});
            if (b) {
                aByteList.add(new byte[]{getFirstByte()});
            }
            aByteList.add(aByte);
            result.add(MysqlUtil.mergeListBytes(aByteList));
            mysqlTcpInfo.addIndex();
        }
        return result;

    }

    @Override
    public MysqlThisRequestInfo getMysqlThisRequestInfo() {
        return mysqlThisRequestInfo;
    }

    /**
     * 返回没有前面长度位或标志位的字节组
     *
     * @return
     */
    protected abstract List<byte[]> toByteNoMarkIndex();
}
