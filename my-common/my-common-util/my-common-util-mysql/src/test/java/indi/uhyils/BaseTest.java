package indi.uhyils;

import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月05日 18时20分
 */
@Transactional
@SpringBootTest(classes = Main.class)
public class BaseTest {

    private Long start;

    @BeforeEach
    public void setUp() throws Exception {
        LogUtil.info("before");
        start = System.currentTimeMillis();
    }

    @AfterEach
    public void tearDown() throws Exception {
        LogUtil.info("after");
        long l = System.currentTimeMillis() - start;
        LogUtil.info("执行时间: " + l);
    }

    @Test
    public void test() {
        Asserts.assertTrue(true);
    }
}
