package indi.uhyils.protocol.mysql.util;

import indi.uhyils.util.Asserts;
import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月09日 20时24分
 */
public class MysqlUtilTest {

    @Test
    public void toBytes() {
        byte[] bytes = MysqlUtil.toBytes(0xFF);
        Asserts.assertTrue(bytes.length == 2);
        bytes = MysqlUtil.toBytes(0x4000);
        Asserts.assertTrue(bytes.length == 4);
        bytes = MysqlUtil.toBytes(0x0200);
        Asserts.assertTrue(bytes.length == 3);


        Asserts.assertTrue(bytes[0] == 0x2);
        Asserts.assertTrue(bytes[1] == 0x0);
        Asserts.assertTrue(bytes[2] == 0x0);
    }
    @Test
    public void getSize() {
        int length = MysqlUtil.getBytesSize(0xFF);
        Asserts.assertTrue(length == 2);
        length = MysqlUtil.getBytesSize(0x4000);
        Asserts.assertTrue(length == 4);
        length = MysqlUtil.getBytesSize(0x0200);
        Asserts.assertTrue(length == 3);
    }
}
