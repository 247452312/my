package indi.uhyils.util.formula.impl.node;

import indi.uhyils.util.formula.FormulaNode;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 正常节点
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月22日 14时27分
 */
public class NormalFormulaNodeImpl extends AbstractFormulaNode implements FormulaNode {

    /**
     * @param formula
     */
    public NormalFormulaNodeImpl(String formula) {
        super(formula);
    }

    /**
     * 解析公式
     */
    @Override
    protected void init() {
        // 替换方法
        replaceFunction();
        // 替换括号
        replaceBrackets();
        // 替换项
        replaceTerm();

        // 查询最终的自变量
        findAndFillVarName();
    }

    @Override
    protected String haveVarNameDerivation(String varName) {
        String formula = getNodeFormula();
        Map<String, FormulaNode> nodeMap = lastNodes();
        for (Entry<String, FormulaNode> entry : nodeMap.entrySet()) {
            String key = entry.getKey();
            FormulaNode value = entry.getValue();
            formula = formula.replace(key, value.derivation(varName));
        }
        return formula;
    }
}
