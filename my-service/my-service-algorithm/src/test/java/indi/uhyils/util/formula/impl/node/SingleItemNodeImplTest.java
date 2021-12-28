package indi.uhyils.util.formula.impl.node;

import indi.uhyils.util.Asserts;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月28日 09时43分
 */
class SingleItemNodeImplTest {

    @Test
    void haveVarNameDerivation() {
        SingleItemNodeImpl singleItem = new SingleItemNodeImpl("2^x");
        String x = singleItem.haveVarNameDerivation("x");
        Asserts.assertTrue(Objects.equals(x, "2 ^ (x) * ln(2) * 1"));
    }

    @Test
    void haveVarNameDerivation2() {
        SingleItemNodeImpl singleItem = new SingleItemNodeImpl("x*y^-4/x");
        String x = singleItem.haveVarNameDerivation("x");
        Asserts.assertTrue(Objects.equals(x, "0+1*(((y))^-4)*((((x))^-1))+((x))*(((y))^-4)*-1 * (x) ^ -2.0 * 1"));
    }
}
