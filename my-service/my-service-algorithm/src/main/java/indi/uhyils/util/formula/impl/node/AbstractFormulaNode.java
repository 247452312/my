package indi.uhyils.util.formula.impl.node;

import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.StringUtil;
import indi.uhyils.util.formula.FormulaNode;
import indi.uhyils.util.formula.FormulaNodeFactory;
import indi.uhyils.util.formula.pojo.BracketsStackItem;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 节点抽象
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月24日 08时37分
 */
public abstract class AbstractFormulaNode implements FormulaNode {

    /**
     * 节点公式原型
     */
    protected String formula;

    /**
     * 下一层的公式的节点
     */
    protected Map<String, FormulaNode> lastNodes;


    /**
     * 最终的符号
     */
    protected List<String> varNames;

    /**
     * 变量递增值
     */
    private Integer varIndex = 0;

    protected AbstractFormulaNode(String formula) {
        this.formula = formula;
        lastNodes = new HashMap<>();

        init();
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
        String result = this.formula;
        for (Entry<String, FormulaNode> entry : lastNodes.entrySet()) {
            String key = entry.getKey();
            FormulaNode value = entry.getValue();
            String tempFormula = value.getFormula();
            tempFormula = dealFormula(tempFormula);

            result = result.replace(key, tempFormula);
        }
        return result;
    }

    @Override
    public String derivation(String varName) {
        if (!contains(varName)) {
            return "0";
        }
        return haveVarNameDerivation(varName);
    }

    @Override
    public Boolean contains(String symbol) {
        List<String> allVarName = getAllVarName();
        return allVarName.contains(symbol);
    }

    @Override
    public List<String> getAllVarName() {
        List<String> result = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(varNames)) {
            result.addAll(varNames);
        }
        for (Entry<String, FormulaNode> entry : lastNodes.entrySet()) {
            FormulaNode value = entry.getValue();
            List<String> allVarName = value.getAllVarName();
            result.addAll(allVarName);
        }
        return result.stream().filter(t -> !t.startsWith(VAR_NAME_PREX)).distinct().collect(Collectors.toList());
    }

    protected abstract void init();

    /**
     * 不存在没有此变量的情况的求导
     *
     * @param varName
     *
     * @return
     */
    protected abstract String haveVarNameDerivation(String varName);

    /**
     * 处理公式串
     *
     * @param formula
     *
     * @return
     */
    protected String dealFormula(String formula) {
        return String.format("(%s)", formula);
    }

    /**
     * 替换方法,具体方法见{@link FormulaNode#FUNCTION_NAME}
     */
    protected void replaceFunction() {
        for (String function : FUNCTION_NAME) {
            int functionStartIndex = formula.indexOf(function);
            if (functionStartIndex == -1) {
                continue;
            }
            int i = functionStartIndex + function.length();
            //指针移至方法后的第一个括号或者最后
            while (i < function.length() && function.charAt(i) != '(') {
                i++;
            }
            // 说明没有括号, 不允许
            if (i == function.length() + 1) {
                Asserts.throwException("公式格式错误,方法后没有跟括号,不允许");
            }
            // 多于的括号
            int bracketsCount = 0;
            // 结束括号的下标
            int endBracketsIndex = -1;

            // 查询括号的数量
            for (int j = i + 1; j < formula.length(); j++) {
                char c = formula.charAt(j);
                if (c == '(') {
                    bracketsCount++;
                } else if (c == ')') {
                    if (bracketsCount == 0) {
                        endBracketsIndex = j;
                        break;
                    }
                    bracketsCount--;
                }
            }
            if (endBracketsIndex == -1) {
                Asserts.throwException("公式格式错误,方法:{} ,后括号不匹配", function);
            }

            String substring = formula.substring(functionStartIndex, endBracketsIndex + 1);
            String name = getLastVarName();
            lastNodes.put(name, new FunctionFormulaNodeImpl(substring, function));
            formula = StringUtil.replaceFirst(formula, substring, name);
        }
    }

    /**
     * 获取下一个替换变量的名称
     *
     * @return
     */
    protected String getLastVarName() {
        return VAR_NAME_PREX + varIndex++;
    }

    /**
     * 查询最终的自变量
     */
    protected void findAndFillVarName() {
        Stream<String> stream = Arrays.stream(new String[]{formula});
        for (String s : SPECIAL_OPERATOR) {
            stream = stream.map(t -> t.split(s)).flatMap(Arrays::stream).filter(StringUtil::isNotEmpty).distinct();
        }
        varNames = stream.map(String::trim).filter(StringUtil::isNotEmpty).distinct().collect(Collectors.toList());
        Iterator<String> iterator = varNames.iterator();
        while (iterator.hasNext()) {
            String varName = iterator.next();

            if (NumberUtils.isParsable(varName)) {
                iterator.remove();
                continue;
            }
            char c = varName.charAt(0);
            if (c >= '0' && c <= '9') {
                Asserts.throwException("符号首位不允许使用数字");
            }
        }
    }

    /**
     * 替换括号
     */
    protected void replaceBrackets() {
        Deque<BracketsStackItem> stack = new ArrayDeque<>();
        Map<String, String> nameFormulaMap = new HashMap<>();
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
                    String substring = formula.substring(i + 1, lastIndex).trim();
                    if (lastNodes.values().stream().map(FormulaNode::getNodeFormula).noneMatch(substring::contains)) {
                        lastNodes.put(name, FormulaNodeFactory.create(substring));
                        nameFormulaMap.put(name, substring);
                    }
                }
            }
        }

        //替换公式中下一层的括号节点
        for (Entry<String, FormulaNode> entry : lastNodes.entrySet()) {
            String name = entry.getKey();
            String s = nameFormulaMap.get(name);
            formula = StringUtil.replaceFirst(formula, "(" + s + ")", name);
        }
    }

    /**
     * 替换每一项(+,-)
     */
    protected void replaceTerm() {
        List<String> formulas = Arrays.stream(formula.split("\\+")).map(t -> t.split("-")).flatMap(Arrays::stream).map(String::trim).collect(Collectors.toList());
        if (formulas.size() == 1) {
            return;
        }
        for (String lastNodeFormula : formulas) {
            String trim = lastNodeFormula.trim();
            // 如果在替换括号中已经存在了.说明已经是替换后的变量($0这种) 就不再替换了
            if (lastNodes.containsKey(trim)) {
                continue;
            }
            if (lastNodeFormula.startsWith(VAR_NAME_PREX)) {
                lastNodes.put(trim, new LevelFormulaNodeImpl(trim));
            } else {
                String name = getLastVarName();
                lastNodes.put(name, new SingleItemNodeImpl(trim));
                formula = StringUtil.replaceFirst(formula, trim, name);
            }

        }
    }

}
