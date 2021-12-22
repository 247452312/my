package indi.uhyils.util.formula.impl;

import indi.uhyils.util.Asserts;
import indi.uhyils.util.StringUtil;
import indi.uhyils.util.formula.FormulaNode;
import indi.uhyils.util.formula.pojo.BracketsStackItem;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月22日 14时27分
 */
public class FormulaNodeImpl implements FormulaNode {

    /**
     * 变量前缀
     */
    private static final String VAR_NAME_PREX = "$";

    /**
     * 符号
     */
    private static final String[] SPECIAL_OPERATOR = new String[]{"\\\\+", "-", "\\\\*", "/", "^"};

    /**
     * 这个节点代表的公式
     */
    private String formula;

    /**
     * 变量递增值
     */
    private Integer varIndex = 0;

    /**
     * 最终的符号
     */
    private List<String> varNames;

    /**
     * 下一层的公式的节点
     */
    private Map<String, FormulaNode> lastNodes;


    public FormulaNodeImpl(String formula) {
        this.formula = formula;
        init();
    }

    /**
     * 解析公式
     */
    private void init() {
        lastNodes = new HashMap<>();
        // 替换括号
        replaceBrackets();

        // 替换项
        replaceTerm();

        // 查询最终的符号
        findAndFillVarName();
    }

    /**
     * 查找变量与符号
     */
    private void findAndFillVarName() {
        Stream<String> stream = Arrays.stream(new String[]{formula});
        for (String s : SPECIAL_OPERATOR) {
            String finalS = Matcher.quoteReplacement(s);
            stream = stream.map(t -> t.split(finalS)).flatMap(Arrays::stream).filter(StringUtil::isNotEmpty).distinct();
        }
        varNames = stream.distinct().collect(Collectors.toList());
        for (String varName : varNames) {
            char c = varName.charAt(0);
            if (c >= '0' && c <= '9') {
                Asserts.assertException("符号首位不允许使用数字");
            }
        }
    }

    /**
     * 替换每一项(+,-)
     */
    private void replaceTerm() {
        List<String> formulas = Arrays.stream(formula.split("\\+")).map(t -> t.split("-")).flatMap(Arrays::stream).collect(Collectors.toList());
        if (formulas.size() == 1) {
            return;
        }
        for (String lastNodeFormula : formulas) {
            String name = getLastVarName();
            String trim = lastNodeFormula.trim();
            lastNodes.put(name, new FormulaNodeImpl(trim));
            formula = StringUtil.replaceFirst(formula, trim, name);
        }
    }

    /**
     * 替换括号
     */
    private void replaceBrackets() {
        Stack<BracketsStackItem> stack = new Stack<>();
        for (int i = formula.length() - 1; i >= 0; i--) {
            char c = formula.charAt(i);
            if (BracketsStackItem.BARCKETS_CORRESPONDING_MAP.containsKey(c)) {
                stack.push(new BracketsStackItem(c, i));
                continue;
            }
            if (BracketsStackItem.BARCKETS_CORRESPONDING_MAP.containsValue(c)) {
                BracketsStackItem peek = stack.peek();
                if (peek.matching(c)) {
                    BracketsStackItem pop = stack.pop();
                    Integer lastIndex = pop.getIndex();
                    String name = getLastVarName();
                    String substring = formula.substring(i, lastIndex).trim();
                    if (lastNodes.values().stream().map(FormulaNode::getNodeFormula).noneMatch(substring::contains)) {
                        lastNodes.put(name, new FormulaNodeImpl(substring));
                    }
                }
            }
        }
        replaceFormulaForLastNode();
    }

    /**
     * 替换公式中下一层的节点
     */
    private void replaceFormulaForLastNode() {
        for (Entry<String, FormulaNode> entry : lastNodes.entrySet()) {
            String name = entry.getKey();
            FormulaNode lastNode = entry.getValue();
            String nodeFormula = lastNode.getNodeFormula();
            formula = StringUtil.replaceFirst(formula, nodeFormula, name);
        }
    }

    private String getLastVarName() {
        return VAR_NAME_PREX + varIndex++;
    }

    @Override
    public Map<String, FormulaNode> lastNodes() {
        return lastNodes;
    }

    @Override
    public String getNodeFormula() {
        return formula;
    }

    @Override
    public String getFormula() {
        for (Entry<String, FormulaNode> entry : lastNodes.entrySet()) {
            String key = entry.getKey();
            FormulaNode value = entry.getValue();
            String formula = value.getFormula();
            formula = String.format("(%s)", formula);
            this.formula = this.formula.replace(key, formula);
        }
        return this.formula;
    }

}
