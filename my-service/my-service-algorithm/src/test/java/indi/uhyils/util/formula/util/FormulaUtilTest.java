package indi.uhyils.util.formula.util;

import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年01月12日 08时13分
 */
class FormulaUtilTest {

    @Test
    void simplification1() {
        String formula = "x^2 + (x * y) + x^2 * y";
        String simplification = FormulaUtil.simplification(formula);
        System.out.println(simplification);
    }


    @Test
    void simplification2() {
        String formula = "2 * x";
        String simplification = FormulaUtil.simplification(formula);
        System.out.println(simplification);
    }


    @Test
    void simplification3() {
        String formula = "2 * x + sin(x + y * z)";
        String simplification = FormulaUtil.simplification(formula);
        System.out.println(simplification);
    }


    @Test
    void simplification4() {
        String formula = "x * y";
        String simplification = FormulaUtil.simplification(formula);
        System.out.println(simplification);
    }


}
