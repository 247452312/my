package indi.uhyils.util.python;

import indi.uhyils.util.Asserts;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月16日 10时03分
 */
class PythonUtilTest {

    @Test
    void executePython() {
    }

    @Test
    void testExecutePython() {
    }

    @Test
    void testExecutePython1() {
    }

    @Test
    void testExecutePython2() throws IOException {
        InputStream charSequenceInputStream = new CharSequenceInputStream("def print_hi(a, b):\n"
                                                                          + "    return a + b", StandardCharsets.UTF_8);
        Object result = PythonUtil.executePython(new InputStream[]{charSequenceInputStream}, "print_hi", new Object[]{1, 2});
        Asserts.assertTrue(Objects.equals(result, 3));

    }
}
