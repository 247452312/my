package indi.uhyils.util;


import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 14时10分
 */
public class AssertsTest {

    @Test
    public void assertException() {
        Asserts.assertException(() -> Asserts.assertException(() -> {
        }));
    }
}
