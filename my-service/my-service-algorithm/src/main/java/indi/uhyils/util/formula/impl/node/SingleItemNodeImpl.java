package indi.uhyils.util.formula.impl.node;

import indi.uhyils.util.Asserts;
import indi.uhyils.util.StringUtil;
import indi.uhyils.util.formula.FormulaNode;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 单项节点,没有加减,只有一项
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月24日 17时13分
 */
public class SingleItemNodeImpl extends AbstractFormulaNode implements FormulaNode {


    public SingleItemNodeImpl(String formula) {
        super(formula);
    }

    @Override
    protected void init() {
        // 验证是否不存在多项
        validateSingle();
        // 切分乘除
        splitMultiplicationAndDivision();
    }

    @Override
    protected String haveVarNameDerivation(String varName) {

        String nodeFormula = getNodeFormula();
        if (nodeFormula.contains("*")) {
            return multiplicationDerivation(varName);
        }

        if (nodeFormula.contains("^")) {
            return powerDerivation(varName);
        }
        String result = this.formula;
        Map<String, FormulaNode> nodeMap = lastNodes();
        for (Entry<String, FormulaNode> entry : nodeMap.entrySet()) {
            String key = entry.getKey();
            FormulaNode value = entry.getValue();
            result = result.replace(key, value.derivation(varName));
        }

        return result;
    }

    /**
     * 幂次求导
     *
     * @param varName
     *
     * @return
     */
    private String powerDerivation(String varName) {
        String nodeFormula = getNodeFormula();
        String[] split = nodeFormula.split("\\^");
        Asserts.assertTrue(split.length == 2);
        Map<String, FormulaNode> nodeMap = lastNodes();
        if (NumberUtils.isParsable(split[0]) && NumberUtils.isParsable(split[1])) {
            return "0";
        }
        // 如果底数为常数,则 f(x) = bl ^ h(x)
        // 求导为: f`(x) = bl ^ h(x) * ln(bl) * h`(x)
        if (NumberUtils.isParsable(split[0]) && !NumberUtils.isParsable(split[1])) {
            String result = "bl ^ h(x) * ln(bl) * h`(x)";
            String bl = split[0];
            FormulaNode formulaNode = nodeMap.get(split[1]);
            String hx = formulaNode.getFormula();
            String hdx = formulaNode.derivation(varName);
            result = result.replace("bl", bl);
            result = result.replace("h(x)", hx);
            result = result.replace("h`(x)", hdx);
            return result;
        }

        // 公式 f(x) = h(x) ^ bl
        // f`(x) = bl * h(x) ^ (bl-1) * h`(x)
        if (!NumberUtils.isParsable(split[0]) && NumberUtils.isParsable(split[1])) {
            String result = "bl * h(x) ^ blLastOne * h`(x)";
            String bl = split[1];
            double v = NumberUtils.toDouble(bl);
            double blLastOne = v - 1;
            FormulaNode formulaNode = nodeMap.get(split[0]);
            String hx = formulaNode.getFormula();
            String hdx = formulaNode.derivation(varName);
            result = result.replace("blLastOne", blLastOne + "");
            result = result.replace("bl", bl);
            result = result.replace("h(x)", hx);
            result = result.replace("h`(x)", hdx);
            return result;
        }
        // 公式: f(x) = g(x) ^ h(x)
        // 求导: f`(x) = f(x) * ((h(x) * g`(x)/g(x) + h`(x) * ln(g(x))))
        FormulaNode gxf = nodeMap.get(split[0]);
        String gx = gxf.getFormula();
        String gdx = gxf.derivation(varName);

        FormulaNode hxf = nodeMap.get(split[1]);
        String hx = hxf.getFormula();
        String hdx = hxf.derivation(varName);

        String fx = getFormula();

        String result = "f(x) * ((h(x) * g`(x)/g(x) + h`(x) * ln(g(x))))";
        result = result.replace("f(x)", String.format("(%s)", fx));
        result = result.replace("h(x)", String.format("(%s)", hx));
        result = result.replace("h`(x)", String.format("(%s)", hdx));
        result = result.replace("g(x)", String.format("(%s)", gx));
        result = result.replace("g`(x)", String.format("(%s)", gdx));

        return result;
    }

    private String multiplicationDerivation(String varName) {
        StringBuilder result = new StringBuilder("0");
        Map<String, FormulaNode> nodeMap = lastNodes();
        for (Entry<String, FormulaNode> entry : nodeMap.entrySet()) {
            String key = entry.getKey();
            FormulaNode value = entry.getValue();
            if (!value.contains(varName)) {
                continue;
            }
            String derivation = value.derivation(varName);
            result.append("+");
            String nodeFormula = getNodeFormula();
            String replace = nodeFormula.replace(key, derivation);

            for (Entry<String, FormulaNode> nodeEntry : nodeMap.entrySet()) {
                if (Objects.equals(nodeEntry.getKey(), key)) {
                    continue;
                }
                replace = replace.replace(nodeEntry.getKey(), String.format("(%s)", nodeEntry.getValue().getFormula()));
            }
            result.append(replace);
        }
        return result.toString();
    }

    private void validateSingle() {
        if (formula.contains("+")) {
            Asserts.throwException("单项节点不允许有加号,如果在括号中请先去除括号");
        }
    }

    /**
     * 切分乘除
     */
    private void splitMultiplicationAndDivision() {
        String replace = null;
        if (formula.contains("*")) {
            replace = this.formula = formula.replace("/", "*1/");
        } else {
            replace = this.formula;
        }
        String[] split = replace.split("\\*");

        if (split.length > 1) {
            for (String s : split) {
                s = s.trim();
                if (NumberUtils.isParsable(s)) {
                    continue;
                }
                String name = getLastVarName();
                lastNodes.put(name, new SingleItemNodeImpl(s));
                this.formula = StringUtil.replaceFirst(this.formula, s, name);
            }
        } else {
            // 没有乘除时代表为单项

            if (this.formula.contains("/")) {
                //替换除法
                replaceDivision(this.formula);
            } else if (this.formula.contains("^")) {
                // 替换幂次计算
                replacePower(this.formula);
            } else {
                //替换根节点
                replaceLevel(this.formula);
            }
        }
    }

    /**
     * 替换根节点
     *
     * @param formula
     */
    private void replaceLevel(String formula) {
        String name = getLastVarName();
        lastNodes.put(name, new LevelFormulaNodeImpl(formula));
        this.formula = StringUtil.replaceFirst(this.formula, formula, name);
    }

    /**
     * 替换幂次计算
     *
     * @param formula
     */
    private void replacePower(String formula) {
        String[] twoItems = formula.split("\\^");
        Asserts.assertTrue(twoItems.length == 2);
        String first = twoItems[0];
        String second = twoItems[1];

        if (!NumberUtils.isParsable(first)) {
            String firstName = getLastVarName();
            lastNodes.put(firstName, new SingleItemNodeImpl(first));
            this.formula = StringUtil.replaceFirst(this.formula, first, firstName);
        }
        if (!NumberUtils.isParsable(second)) {
            String secondName = getLastVarName();
            lastNodes.put(secondName, new SingleItemNodeImpl(second));
            this.formula = StringUtil.replaceFirst(this.formula, second, secondName);
        }
    }

    /**
     * 替换除法
     *
     * @param formula
     */
    private void replaceDivision(String formula) {
        String[] twoItem = formula.split("/");
        Asserts.assertTrue(twoItem.length == 2);
        String first = twoItem[0];
        String second = twoItem[1];

        if (!NumberUtils.isParsable(first)) {
            String firstName = getLastVarName();
            lastNodes.put(firstName, new SingleItemNodeImpl(first));
            this.formula = StringUtil.replaceFirst(this.formula, first, firstName);
        }
        if (!NumberUtils.isParsable(second)) {
            String secondName = getLastVarName();
            second = second + "^-1";
            lastNodes.put(secondName, new SingleItemNodeImpl(second));
            this.formula = StringUtil.replaceFirst(this.formula, formula, secondName);
        }
    }
}
