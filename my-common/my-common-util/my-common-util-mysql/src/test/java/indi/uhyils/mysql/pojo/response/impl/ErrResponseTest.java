package indi.uhyils.mysql.pojo.response.impl;


import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlHandlerStatusEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.util.MysqlUtil;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月06日 14时49分
 */
public class ErrResponseTest {

    @Test
    public void errResponseToByteTest() {
        final MysqlTcpInfo value = new MysqlTcpInfo();
        value.setStatus(MysqlHandlerStatusEnum.PASSED);
        MysqlContent.MYSQL_TCP_INFO.set(value);
        final byte[] bytes = MysqlUtil.mergeListBytes(new ErrResponse(MysqlErrCodeEnum.EE_STAT, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "错误信息").toByte());
        final String dump = MysqlUtil.dump(bytes);
        System.out.println(dump);


    }
}
