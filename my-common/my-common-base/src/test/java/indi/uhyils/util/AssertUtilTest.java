package indi.uhyils.util;

import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 14时10分
 */
public class AssertUtilTest {

    @Test
    public void assertException() {
        AssertUtil.assertException(() -> AssertUtil.assertException(() -> {
        }));
    }
}
