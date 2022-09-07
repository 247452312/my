package indi.uhyils.mysql.enums;


import indi.uhyils.mysql.util.MysqlUtil;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月06日 14时55分
 */
public class MysqlErrCodeEnumTest {

    @Test
    public void getByteCode() {
        final byte[] byteCode = MysqlErrCodeEnum.EE_STAT.getByteCode();
        final String dump = MysqlUtil.dump(byteCode);
        System.out.println(dump);

    }
}
