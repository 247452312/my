package indi.uhyils;

import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月05日 18时20分
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:application.yml"})
@Transactional
@SpringBootTest
public class BaseTest {

    private Long start;

    @Before
    public void setUp() throws Exception {
        LogUtil.info("before");
        start = System.currentTimeMillis();
    }

    @After
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