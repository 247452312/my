package indi.uhyils.util.formula.impl.node;

import com.alibaba.nacos.common.utils.Objects;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.formula.FormulaNode;

/**
 * 根节点, 只有一个变量,但是有多种常数
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月24日 08时38分
 */
public class LevelFormulaNodeImpl extends AbstractFormulaNode implements FormulaNode {

    private String varName;

    public LevelFormulaNodeImpl(String formula) {
        super(formula);
        Asserts.assertTrue(varNames.size() == 1);
        this.varName = varNames.get(0);
    }

    @Override
    protected void init() {
        // 查询最终的自变量
        findAndFillVarName();
    }

    @Override
    protected String haveVarNameDerivation(String varName) {
        // 如果本节点变量中包含了varName,说明这是一个根节点
        if (Objects.equals(this.varName, varName)) {
            return "1";
        }
        return "0";
    }
}
