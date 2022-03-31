package indi.uhyils;

import indi.uhyils.util.Asserts;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月30日 09时20分
 */
class PlanUtilTest extends BaseTest {

    @Test
    void analysisSql() {
        String sql = "select * from sys_user a where a.id = 12 ";
        Asserts.assertNoException(() -> PlanUtil.analysisSql(sql));
    }
}

