package indi.uhyils.util.formula.impl;

import indi.uhyils.util.Asserts;
import indi.uhyils.util.formula.FormulaNode;
import indi.uhyils.util.formula.FormulaNodeFactory;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月22日 15时56分
 */
class FormulaNodeImplTest {

    @Test
    public void testFormulaNode() {
        FormulaNode node = FormulaNodeFactory.create("x^2 + (x * y) + x^2 * y");
        String nodeFormula = node.getNodeFormula();
        Asserts.assertTrue(Objects.equals("$1 + $0 + $2", nodeFormula));
        Asserts.assertTrue(Objects.equals("(((x))^2) + (((x)) * ((y))) + ((((x))^2) * ((y)))", node.getFormula()));
    }

    @Test
    public void testFormulaNode2() {
        FormulaNode node = FormulaNodeFactory.create("2 * x");
        String nodeFormula = node.getFormula();
        Asserts.assertTrue(Objects.equals("2 * ((x))", nodeFormula));
    }

    @Test
    public void testFormulaNode3() {
        FormulaNode node = FormulaNodeFactory.create("2 * x + sin(x + y * z)");
        String nodeFormula = node.getNodeFormula();
        Asserts.assertTrue(Objects.equals("$1 + $0", nodeFormula));
        Map<String, FormulaNode> nodeMap = node.lastNodes();
        FormulaNode firstNode = nodeMap.get("$0");
        FormulaNode secondNode = nodeMap.get("$1");
        String nodeFormula1 = firstNode.getNodeFormula();
        Asserts.assertTrue(Objects.equals(nodeFormula1, "sin($0 + $1)"));
        String nodeFormula2 = secondNode.getNodeFormula();
        Asserts.assertTrue(Objects.equals(nodeFormula2, "2 * $0"));
    }

    @Test
    public void testFormulaNode4() {
        FormulaNode node = FormulaNodeFactory.create("x^2 + (x * y) + x^2 * y");
        String nodeFormula = node.derivation("x");
    }

    @Test
    public void testFormulaNode5() {
        FormulaNode node = FormulaNodeFactory.create("2 * x + sin(x + y * z)");
        String nodeFormula = node.derivation("x");
    }


    @Test
    public void testFormulaNode6() {
        FormulaNode node = FormulaNodeFactory.create("x * y");
        String nodeFormula = node.derivation("x");
        Asserts.assertEqual(nodeFormula, "0+1 * ((y))");
    }


}
