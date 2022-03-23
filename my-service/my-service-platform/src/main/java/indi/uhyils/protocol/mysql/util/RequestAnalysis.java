package indi.uhyils.protocol.mysql.util;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.protocol.mysql.decode.Proto;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月22日 09时22分
 */
public class RequestAnalysis {

    private RequestAnalysis() {
    }

    public static MysqlCommandTypeEnum load(MysqlThisRequestInfo mysqlThisRequestInfo) {
        byte[] mysqlBytes = mysqlThisRequestInfo.getMysqlBytes();
        Proto proto = new Proto(mysqlBytes, 4);
        long type = proto.getFixedInt(1);
        return MysqlCommandTypeEnum.parse(type);
    }

}
