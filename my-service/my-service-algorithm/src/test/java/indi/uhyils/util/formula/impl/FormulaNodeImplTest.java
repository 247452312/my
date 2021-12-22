package indi.uhyils.util.formula.impl;

import indi.uhyils.util.Asserts;
import indi.uhyils.util.formula.FormulaNode;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月22日 15时56分
 */
class FormulaNodeImplTest {


    @Test
    public void testFormulaNode() {
        FormulaNode node = new FormulaNodeImpl("x^2 + x * y + x^2 * y");
        String nodeFormula = node.getNodeFormula();
        Asserts.assertTrue(Objects.equals("$0 + $1 + $2", nodeFormula));
        Asserts.assertTrue(Objects.equals("(x^2) + (x * y) + (x^2 * y)", node.getFormula()));
    }

    @Test
    public void testFormulaNode2() {
        FormulaNode node = new FormulaNodeImpl("2 * x");
        String nodeFormula = node.getNodeFormula();
        Asserts.assertTrue(Objects.equals("2 * x", nodeFormula));
    }


}
