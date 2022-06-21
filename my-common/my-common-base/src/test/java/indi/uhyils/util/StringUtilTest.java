package indi.uhyils.util;

import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年01月12日 09时36分
 */
class StringUtilTest {

    @Test
    void find() {
        String str = "asodjhbg;aiosdhg+asjbe_fhajkv";
        String s = "a*b";
        Map<Integer, String> integerStringMap = StringUtil.find(str, s);

    }
}
