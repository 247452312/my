package indi.uhyils.protocol.mysql.netty.impl;

import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.response.impl.ResultSetResponse;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.protocol.mysql.netty.impl.other.MysqlNettyTest;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月23日 14时37分
 */
public class MysqlTransTest {


    @Test
    void trans() throws InterruptedException {
        MysqlNettyTest mysqlNettyTest = new MysqlNettyTest();
        mysqlNettyTest.init();
        Thread.sleep(100000000L);
    }


    @Test
    void responseByteTest() {
        final MysqlTcpInfo mysqlTcpInfo = new MysqlTcpInfo();
        final ArrayList<FieldInfo> fields = new ArrayList<>();
        short ss = 0;
        fields.add(new FieldInfo("testDB", "sys_table", "sys_table", "id", "id", 100, 1, FieldTypeEnum.FIELD_TYPE_LONG, ss, (byte) 0, null, 1));
        fields.add(new FieldInfo("testDB", "sys_table", "sys_table", "name", "name", 100, 2, FieldTypeEnum.FIELD_TYPE_STRING, ss, (byte) 0, null, 1));
        final ArrayList<Map<String, Object>> jsonInfo = new ArrayList<>();
        final HashMap<String, Object> firstLine = new HashMap<>();
        firstLine.put("id", 1L);
        firstLine.put("name", "name1");
        jsonInfo.add(firstLine);
        final ResultSetResponse e = new ResultSetResponse(mysqlTcpInfo, fields, jsonInfo);
        final List<byte[]> bytes = e.toByte();
        for (byte[] aByte : bytes) {
            LogUtil.info("\n" + MysqlUtil.dump(aByte));
        }
    }

}
