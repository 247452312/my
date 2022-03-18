package indi.uhyils.pojo.entity.type;

import indi.uhyils.util.Asserts;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月17日 09时07分
 */
class PasswordTest {

    @Test
    void encode() {
        Password password = new Password("123456");
        String encode = password.encode();
        Asserts.assertTrue(Objects.equals(encode, "lMNDs2a/MP9N0FStLWVEDQ=="));
    }

    @Test
    void decode() {
        Password password = new Password("lMNDs2a/MP9N0FStLWVEDQ==");
        String decode = password.decode();
        Asserts.assertTrue(Objects.equals(decode, "123456"));
    }
}
