package indi.uhyils.util.formula.util;

import indi.uhyils.annotation.NotNull;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.StringUtil;
import indi.uhyils.util.formula.FormulaNode;
import indi.uhyils.util.formula.enums.FunctionEnum;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年01月10日 15时48分
 */
public class FormulaUtil {


    /**
     * 化简
     *
     * @param formula 化简前公式
     *
     * @return 化简后公式
     */
    public static String simplification(String formula) {
        formula = formula.replace(" ", "");
        //去除零项
        formula = simplificationZero(formula);
        // 处理公式项
        formula = simplificationFunction(formula);
        // 去除括号项
        formula = simplificationBracket(formula);
        return formula;
    }

    /**
     * 化简处理公式项
     *
     * @param formula
     *
     * @return
     */
    private static String simplificationFunction(String formula) {
        for (FunctionEnum value : FunctionEnum.values()) {
            String name = value.getName();
            int i = formula.indexOf(name);
            if (i == -1) {
                continue;
            }
            List<Integer> indexs = StringUtil.find(formula, name + "(*)");

        }
        return null;
    }

    /**
     * 去除零项
     *
     * @param formula
     *
     * @return
     */
    private static String simplificationZero(String formula) {
        formula = formula.replace("+0+", "+");
        formula = formula.replace("+0-", "-");
        formula = formula.replace("-0+", "+");
        formula = formula.replace("-0-", "-");
        formula = formula.replace("(0-", "(");
        formula = formula.replace("(0+", "(");
        return formula;
    }

    /**
     * 去除可以去除的括号
     *
     * @param formula
     *
     * @return
     */
    @NotNull
    public static String simplificationBracket(String formula) {

        // key -> startBracketIndex value -> toEndBracketLength
        Map<Integer, Integer> bracketIndexMap = new HashMap<>(16);
        Stack<Integer> bracketStack = new Stack<>();

        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if (c == '(') {
                bracketStack.push(i);
                continue;
            }
            if (c == ')') {
                Asserts.assertTrue(!bracketStack.isEmpty(), "括号不匹配:{}", formula);
                Integer startBracketIndex = bracketStack.pop();
                if (bracketStack.isEmpty()) {
                    bracketIndexMap.put(startBracketIndex, i - startBracketIndex);
                }
            }
        }

        if (bracketIndexMap.isEmpty()) {
            return formula;
        }

        // 前置处理
        int changeIndex = 0;
        Map<String, String> replaceStrAndOriginalStrMap = new HashMap<>(bracketIndexMap.size());
        for (Integer startIndex : bracketIndexMap.keySet().stream().sorted(Comparator.comparingInt(t -> -t)).collect(Collectors.toList())) {
            Integer length = bracketIndexMap.get(startIndex);
            // 替换修改字符串
            String targetStr = FormulaNode.VAR_NAME_PREX + changeIndex++;
            String substring = formula.substring(startIndex, startIndex + length + 1);
            replaceStrAndOriginalStrMap.put(targetStr, substring);
            formula = StringUtil.replaceByIndexAndLength(formula, startIndex, length + 1, targetStr);
        }

        // 化简加减乘除后的括号
        simplificationAdditionAndSubtraction(formula, replaceStrAndOriginalStrMap);
        for (Entry<String, String> entry : replaceStrAndOriginalStrMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            value = simplification(value);
            formula = formula.replace(key, value);
        }
        return formula;
    }

    /**
     * 化简加减前后的括号
     * 1. 括号外是加减,括号除了公式都可以删除
     *
     * @param formula
     * @param replaceStrAndOriginalStrMap
     */
    private static void simplificationAdditionAndSubtraction(String formula, Map<String, String> replaceStrAndOriginalStrMap) {
        List<String> cutByAdditionAndSubtraction = Arrays.stream(formula.split("\\+")).map(t -> t.split("-")).flatMap(Arrays::stream).collect(Collectors.toList());
        for (String part : cutByAdditionAndSubtraction) {
            if (part.startsWith(FormulaNode.VAR_NAME_PREX)) {
                String targetStr = replaceStrAndOriginalStrMap.get(part);
                targetStr = targetStr.substring(1, targetStr.length() - 1);
                replaceStrAndOriginalStrMap.put(part, targetStr);
            } else {
                simplificationMultiplicationAndDivision(part, replaceStrAndOriginalStrMap);
            }
        }
    }

    /**
     * 化简乘除前后的括号
     *
     * @param part                        需要化简的部分
     * @param replaceStrAndOriginalStrMap 替换的部分
     */
    private static void simplificationMultiplicationAndDivision(String part, Map<String, String> replaceStrAndOriginalStrMap) {
        List<String> cutByMultiplicationAndDivision = Arrays.stream(part.split("\\*")).map(t -> t.split("/")).flatMap(Arrays::stream).collect(Collectors.toList());
        for (String part2 : cutByMultiplicationAndDivision) {
            if (part2.startsWith(FormulaNode.VAR_NAME_PREX)) {
                String targetStr2 = replaceStrAndOriginalStrMap.get(part2);
                if (StringUtil.allNotContains(targetStr2, '+', '-')) {
                    targetStr2 = targetStr2.substring(1, targetStr2.length() - 1);
                    replaceStrAndOriginalStrMap.put(part2, targetStr2);
                }
            }
        }
    }

}
