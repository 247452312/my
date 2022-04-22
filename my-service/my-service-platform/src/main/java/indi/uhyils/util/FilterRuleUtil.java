package indi.uhyils.util;

import com.google.common.collect.Maps.EntryTransformer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月26日 14时27分
 */
public final class FilterRuleUtil {

    private static final Map<String, OrAndTree> ruleCache = new HashMap<>();

    /**
     * 替换用
     */
    private static final String RULE_KEY_INDEX = "RULE_KEY_{index}";

    private FilterRuleUtil() {
    }

    /**
     * 进行匹配结果逻辑计算
     *
     * @param ruleStr
     * @param line
     *
     * @return
     */
    public static Boolean matchRules(String ruleStr, Map<String, Object> line) {
        OrAndTree ruleTree = makeRuleStrToOrAndTree(ruleStr);
        return ruleTree.makeResults(line, ruleTree);
    }

    /**
     * 将规则字符串解析为树
     *
     * @param ruleStr
     *
     * @return
     */
    private static OrAndTree makeRuleStrToOrAndTree(String ruleStr) {
        ruleStr = cleanBrackets(ruleStr.trim());
        if (ruleCache.containsKey(ruleStr)) {
            // 如果缓存存在,就直接返回缓存
            return ruleCache.get(ruleStr);
        }
        String tempCacheKey = ruleStr;
        List<String> keyList = new ArrayList<>();
        /*原值,key*/
        Map<String, String> valueKeyMap = new HashMap<>();
        /*key,原值*/
        Map<String, String> keyValueMap = new HashMap<>();

        List<Integer> andIndexs = new ArrayList<>();
        List<Integer> orIndexs = new ArrayList<>();
        int ruleKeyIndex = 0;
        Deque<Integer> frontBracketIndex = new ArrayDeque<>();

        for (int i = 0; i < ruleStr.length(); i++) {
            char c = ruleStr.charAt(i);
            if (c == '(') {
                frontBracketIndex.push(i);
                continue;
            }
            if (c == ')') {
                Integer index = frontBracketIndex.pop();
                // 栈中的左括号还存在.说明还在括号中,不做操作
                if (!frontBracketIndex.isEmpty()) {
                    continue;
                }
                String substring = ruleStr.substring(index, i + 1);
                String key = RULE_KEY_INDEX.replace("index", "" + ruleKeyIndex++);
                valueKeyMap.put(substring, key);
                keyValueMap.put(key, substring);
                keyList.add(key);
            }
            // 没有在括号中
            if (frontBracketIndex.isEmpty() && i < ruleStr.length() - 2) {
                String sym = ruleStr.substring(i, i + 2);
                switch (sym) {
                    case "&&":
                        andIndexs.add(i);
                        break;
                    case "||":
                        orIndexs.add(i);
                        break;
                    default:
                        break;
                }
            }
        }
        // 没有&& 和 || 单纯的一个表达式
        if (CollectionUtil.isEmpty(andIndexs) && CollectionUtil.isEmpty(orIndexs)) {
            RuleTree ruleTree = makeRuleStrToRuleTree(ruleStr);
            return new OrAndTree(ruleTree);
        }
        // 有&&和||
        /*1.替换*/
        for (Entry<String, String> entry : valueKeyMap.entrySet()) {
            String value = entry.getKey();
            String key = entry.getValue();
            // 将原值替换为指定的key
            ruleStr = ruleStr.replace(value, key);
        }
        String[] split = ruleStr.split("\\|\\|");
        OrAndTree result;
        if (split.length != 1) {
            OrAndTree firstOrAndTree = makeOrAndTree(keyList, keyValueMap, split[0]);
            for (int i = 1; i < split.length; i++) {
                OrAndTree nextRuleTree = makeOrAndTree(keyList, keyValueMap, split[i]);
                // 或 树
                OrAndTree orAndTree = new OrAndTree(0);
                orAndTree.setLeftTree(firstOrAndTree);
                orAndTree.setRightTree(nextRuleTree);
                firstOrAndTree = orAndTree;
            }
            result = firstOrAndTree;
        } else {
            split = ruleStr.split("&&");
            OrAndTree firstOrAndTree = makeOrAndTree(keyList, keyValueMap, split[0]);
            for (int i = 1; i < split.length; i++) {
                OrAndTree nextRuleTree = makeOrAndTree(keyList, keyValueMap, split[i]);
                // 且 树
                OrAndTree orAndTree = new OrAndTree(1);
                orAndTree.setLeftTree(firstOrAndTree);
                orAndTree.setRightTree(nextRuleTree);
                firstOrAndTree = orAndTree;
            }
            result = firstOrAndTree;
        }
        ruleCache.put(tempCacheKey, result);
        return result;
    }

    private static OrAndTree makeOrAndTree(List<String> keyList, Map<String, String> keyValueMap, String s) {
        String next = s.trim();
        for (int j = keyList.size() - 1; j >= 0; j--) {
            String key = keyList.get(j);
            String value = keyValueMap.get(key);
            next = next.replace(key, value);
        }
        return makeRuleStrToOrAndTree(next);
    }

    /**
     * 清空字符串两边的括号
     *
     * @param ruleStr
     *
     * @return
     */
    private static String cleanBrackets(String ruleStr) {
        if (!ruleStr.startsWith("(") || !ruleStr.endsWith(")")) {
            return ruleStr;
        }
        Deque<Integer> frontBracketIndex = new ArrayDeque<>();
        for (int i = 0; i < ruleStr.length(); i++) {
            char c = ruleStr.charAt(i);
            if (c == '(') {
                frontBracketIndex.push(i);
                continue;
            }
            if (c == ')') {
                Integer pop = frontBracketIndex.pop();
                // 为空时才判断
                if (frontBracketIndex.isEmpty()) {
                    // 括号两端为第一个和最后一个
                    if (pop == 0 && i == ruleStr.length() - 1) {
                        return ruleStr.substring(1, ruleStr.length() - 1);
                    } else {
                        return ruleStr;
                    }
                }
            }
        }
        return ruleStr;
    }

    /**
     * 将规则字符串解析为树
     *
     * @param ruleStr
     *
     * @return
     */
    private static RuleTree makeRuleStrToRuleTree(String ruleStr) {

        RuleSymbol ruleSymbol = RuleSymbol.canParse(ruleStr);
        String symbol = ruleSymbol.getSymbol();
        // 无符号的时候
        if (ruleSymbol == RuleSymbol.NULL) {
            RuleTree ruleTree = new RuleTree(ruleSymbol);
            ruleTree.setValue(ruleStr);
            return ruleTree;
        }
        //有符号的时候
        String[] split = ruleStr.split(symbol);
        Asserts.assertTrue(split.length == 2, "表达式只支持双目表达式");
        RuleTree ruleTree = new RuleTree(ruleSymbol);
        ruleTree.setChild(Arrays.asList(makeRuleStrToRuleTree(split[0]), makeRuleStrToRuleTree(split[1])));
        return ruleTree;
    }

    /**
     * 各种符号
     */
    private enum RuleSymbol {
        /**
         * 等于
         */
        EQ(" == ", (ruleTree, param) -> {
            List<RuleTree> child = ruleTree.getChild();
            Asserts.assertTrue(child != null && child.size() == 2, "节点公式不正确,等号应该将公式分为两部分:{}", ruleTree);
            RuleTree leftTree = child.get(0);
            RuleTree rightTree = child.get(1);

            Object left = leftTree.makeObjectValue(param);
            Object right = rightTree.makeObjectValue(param);
            if (left instanceof Number) {
                return ((Number) left).doubleValue() == ((Number) right).doubleValue();
            }
            return Objects.equals(left, right);
        }),
        /**
         * 不等于
         */
        NE(" != ", (ruleTree, param) -> {
            List<RuleTree> child = ruleTree.getChild();
            Asserts.assertTrue(child != null && child.size() == 2, "节点公式不正确,不等号应该将公式分为两部分:{}", ruleTree);
            RuleTree leftTree = child.get(0);
            RuleTree rightTree = child.get(1);
            Object leftNumber = leftTree.makeObjectValue(param);
            Object rightNumber = rightTree.makeObjectValue(param);
            return !Objects.equals(leftNumber, rightNumber);
        }),
        /**
         * 大于
         */
        GT(" > ", (ruleTree, param) -> {
            List<RuleTree> child = ruleTree.getChild();
            Asserts.assertTrue(child != null && child.size() == 2, "节点公式不正确,大于号应该将公式分为两部分:{}", ruleTree);
            RuleTree leftTree = child.get(0);
            RuleTree rightTree = child.get(1);
            Number leftNumber = leftTree.makeNumberResult(param);
            Number rightNumber = rightTree.makeNumberResult(param);
            return leftNumber.doubleValue() > rightNumber.doubleValue();
        }),
        /**
         * 大于等于
         */
        GE(" >= ", (ruleTree, param) -> {
            List<RuleTree> child = ruleTree.getChild();
            Asserts.assertTrue(child != null && child.size() == 2, "节点公式不正确,大于等号应该将公式分为两部分:{}", ruleTree);
            RuleTree leftTree = child.get(0);
            RuleTree rightTree = child.get(1);
            Number leftNumber = leftTree.makeNumberResult(param);
            Number rightNumber = rightTree.makeNumberResult(param);
            return leftNumber.doubleValue() >= rightNumber.doubleValue();
        }),
        /**
         * 小于
         */
        LT(" < ", (ruleTree, param) -> {
            List<RuleTree> child = ruleTree.getChild();
            Asserts.assertTrue(child != null && child.size() == 2, "节点公式不正确,小于应该将公式分为两部分:{}", ruleTree);
            RuleTree leftTree = child.get(0);
            RuleTree rightTree = child.get(1);
            Number leftNumber = leftTree.makeNumberResult(param);
            Number rightNumber = rightTree.makeNumberResult(param);
            return leftNumber.doubleValue() < rightNumber.doubleValue();
        }),
        /**
         * 小于等于
         */
        LE(" <= ", (ruleTree, param) -> {
            List<RuleTree> child = ruleTree.getChild();
            Asserts.assertTrue(child != null && child.size() == 2, "节点公式不正确,小于等于应该将公式分为两部分:{}", ruleTree);
            RuleTree leftTree = child.get(0);
            RuleTree rightTree = child.get(1);
            Number leftNumber = leftTree.makeNumberResult(param);
            Number rightNumber = rightTree.makeNumberResult(param);
            return leftNumber.doubleValue() <= rightNumber.doubleValue();
        }),
        /**
         * like
         */
        LIKE(" like ", (ruleTree, param) -> {
            List<RuleTree> child = ruleTree.getChild();
            Asserts.assertTrue(child != null && child.size() == 2, "节点公式不正确,like应该将公式分为两部分:{}", ruleTree);
            RuleTree leftTree = child.get(0);
            RuleTree rightTree = child.get(1);
            String beMatchStr = leftTree.makeStringResult(param);
            String matchStr = rightTree.makeStringResult(param);
            Asserts.assertTrue(matchStr.startsWith("'") && matchStr.endsWith("'") && matchStr.length() > 2, "like右边必须以单引号作为开头与结尾");
            matchStr = matchStr.substring(1, matchStr.length() - 1);
            if (!matchStr.contains("%")) {
                return beMatchStr.contains(matchStr);
            }
            String[] split = beMatchStr.split("%");
            List<String> splitNoEmptyArray = Arrays.stream(split).filter(StringUtil::isNotEmpty).collect(Collectors.toList());
            List<Integer> collect = splitNoEmptyArray.stream().map(beMatchStr::indexOf).collect(Collectors.toList());
            boolean endPer = true;
            if (matchStr.endsWith("%")) {
                endPer = beMatchStr.endsWith(splitNoEmptyArray.get(splitNoEmptyArray.size() - 1));
            }
            boolean startPer = true;
            if (beMatchStr.startsWith("%")) {
                startPer = beMatchStr.startsWith(splitNoEmptyArray.get(0));
            }
            boolean sort = MathUtil.isSort(collect.toArray(new Integer[]{}));
            return sort && endPer && startPer;
        }),
        /**
         * in
         */
        IN(" in ", (ruleTree, param) -> {
            List<RuleTree> child = ruleTree.getChild();
            Asserts.assertTrue(child != null && child.size() == 2, "节点公式不正确,in应该将公式分为两部分:{}", ruleTree);
            RuleTree leftTree = child.get(0);
            RuleTree rightTree = child.get(1);
            Object beMatchStr = leftTree.makeStringResult(param);
            if (NumberUtils.isParsable(beMatchStr.toString())) {
                beMatchStr = NumberUtils.toDouble(beMatchStr.toString());
            }
            String matchStr = rightTree.makeStringResult(param);
            Asserts.assertTrue(matchStr.startsWith("(") && matchStr.endsWith(")") && matchStr.length() > 2, "in右边必须以括号作为开头与结尾");
            // 去掉括号
            matchStr = matchStr.substring(1, matchStr.length() - 1);
            List<Object> matchStrList = Arrays.stream(matchStr.split(",")).map(t -> {

                if (NumberUtils.isParsable(t)) {
                    return NumberUtils.toDouble(t);
                }
                return t;
            }).collect(Collectors.toList());
            boolean match = false;
            for (Object s : matchStrList) {
                if (Objects.equals(s, beMatchStr)) {
                    match = true;
                    break;
                }
            }
            return match;
        }),
        /**
         * 无符号
         */
        NULL("", (ruleTree, param) -> {
            return ruleTree.makeBooleanResult(param);
        });

        /**
         * 符号
         */
        private final String symbol;

        /**
         * 计算结果公式
         */
        private final EntryTransformer<RuleTree, Map<String, Object>, Boolean> parseFunction;

        RuleSymbol(String symbol, EntryTransformer<RuleTree, Map<String, Object>, Boolean> parseFunction) {
            this.symbol = symbol;
            this.parseFunction = parseFunction;
        }


        public static RuleSymbol canParse(String symbolStr) {
            /*1.匹配指定结果, 如果匹配到,则准备返回, 如果匹配到两个结果,则返回错误*/
            RuleSymbol result = null;
            for (RuleSymbol value : values()) {
                if (value == RuleSymbol.NULL) {
                    continue;
                }
                int count = StringUtil.containsCount(symbolStr, value.getSymbol());
                // 包含多个. 不允许. 一个过滤器只允许一个
                Asserts.assertTrue(count <= 1, "{},匹配到多个符号:{},不正确", symbolStr, value.getSymbol());
                if (count == 1) {
                    if (result != null && value.getSymbol().contains(result.getSymbol())) {
                        result = null;
                    } else if (result != null && result.getSymbol().contains(value.getSymbol())) {
                        result = value;
                        continue;
                    }
                    Asserts.assertTrue(result == null, "{},匹配到多个符号:{},{},不正确", symbolStr, result != null ? result.getSymbol() : null, value.getSymbol());
                    result = value;
                }
            }

            // 没有匹配到符号. 有可能是 boolean number String
            if (result == null) {
                return RuleSymbol.NULL;
            }
            return result;
        }

        /**
         * 执行方法
         *
         * @param tree
         * @param param
         *
         * @return
         */
        public Boolean transformEntry(RuleTree tree, Map<String, Object> param) {
            return parseFunction.transformEntry(tree, param);
        }

        public String getSymbol() {
            return symbol;
        }
    }

    /**
     * 规则树 例如 a<b 则 symbol为< 节点内容为null, 子树为 a 和 b
     * 其中a子树 symbol和child均为null, value为a
     */
    private static class RuleTree {

        /**
         * 符号
         */
        private final RuleSymbol symbol;

        /**
         * 节点内容
         */
        private String value;

        /**
         * 子树
         */
        private List<RuleTree> child;

        private RuleTree(RuleSymbol symbol) {
            this.symbol = symbol;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<RuleTree> getChild() {
            return child;
        }

        public void setChild(List<RuleTree> child) {
            this.child = child;
        }
/*

        @Override
        public String toString() {
            if (StringUtil.isNotEmpty(value)) {
                return value;
            }
            return child.get(0) + symbol.getSymbol() + child.get(1);
        }
*/

        /**
         * 获取结果
         *
         * @param param
         *
         * @return
         */
        public Boolean makeBooleanResult(Map<String, Object> param) {
            if (value != null) {
                Object valueObj = makeObjectValue(param);
                if (valueObj instanceof Boolean) {
                    return (Boolean) valueObj;
                }
                if (valueObj instanceof String) {
                    return "true".equalsIgnoreCase((String) valueObj);
                }
                if (valueObj instanceof Number) {
                    Number number = (Number) valueObj;
                    return number.doubleValue() != 0.0;
                }
                Asserts.throwException("单独的项目不允许执行");
            }
            Asserts.assertTrue(symbol != null, "不识别的符号");
            return symbol.transformEntry(this, param);
        }

        private Object makeObjectValue(Map<String, Object> param) {
            int index;
            String changeValue = value;
            while ((index = changeValue.indexOf("${")) != -1) {
                int backBarcketIndex = changeValue.indexOf("}", index);
                // ${xxx} 形式
                String substring = changeValue.substring(index, backBarcketIndex + 1);
                // xxx 形式
                String paramKey = substring.substring(2, substring.length() - 1);
                if (param.containsKey(paramKey)) {
                    Object paramItem = param.get(paramKey);
                    Object rep = parseParamItem(paramItem);
                    changeValue = changeValue.replace(substring, rep.toString());
                }
            }
            if (changeValue.contains("+") || changeValue.contains("-")) {
                return MathUtil.calculate(changeValue);
            }
            if (NumberUtils.isParsable(changeValue)) {
                return NumberUtils.toDouble(changeValue);
            }
            return changeValue;
        }

        /**
         * 解析item为实际的返回
         *
         * @param paramItem
         *
         * @return
         */
        private Object parseParamItem(Object paramItem) {
            Object rep = "";
            if (paramItem instanceof Boolean) {
                rep = Boolean.TRUE.equals(paramItem) ? 1 : 0;
            } else if (paramItem instanceof String) {
                if ("true".equals(paramItem)) {
                    rep = 1;
                } else if ("false".equals(paramItem)) {
                    rep = 0;
                } else {
                    rep = paramItem;
                }
            } else if (paramItem instanceof Number) {
                Number number = (Number) paramItem;
                rep = number.doubleValue();
            } else if (paramItem instanceof Date) {
                // todo date支持日期
                rep = ((Date) paramItem).getTime();
            } else {
                Asserts.throwException("报错,入参暂不允许除Boolean,String,Number之外的其他类型,传入类型:{}", paramItem.getClass().getName());
            }
            return rep;
        }

        public Number makeNumberResult(Map<String, Object> param) {
            Object o = makeObjectValue(param);
            if (NumberUtils.isParsable(o.toString())) {
                return NumberUtils.toDouble(o.toString());
            }
            Asserts.throwException("指定值不是number,不能转换");
            return null;
        }

        public String makeStringResult(Map<String, Object> param) {
            int index;
            String changeValue = value;
            while ((index = changeValue.indexOf("${")) != -1) {
                int backBarcketIndex = changeValue.indexOf("}", index);
                // ${xxx} 形式
                String substring = changeValue.substring(index, backBarcketIndex + 1);
                // xxx 形式
                String paramKey = substring.substring(2, substring.length() - 1);
                if (param.containsKey(paramKey)) {
                    Object o = param.get(paramKey);
                    String str = null;
                    if (o instanceof Boolean) {
                        str = Boolean.TRUE.equals(o) ? "true" : "false";
                    } else if (o instanceof String) {
                        str = "\"" + o + "\"";
                    } else if (o instanceof Number) {
                        Number number = (Number) o;
                        str = number.toString();
                    } else if (o instanceof Date) {
                        // todo date支持多种格式
                        str = Long.toString(((Date) o).getTime());
                    } else {
                        Asserts.throwException("报错,入参暂不允许除Boolean,String,Number之外的其他类型,传入类型:{}", o.getClass().getName());
                    }
                    changeValue = changeValue.replace(substring, str);
                }
            }
            return changeValue;
        }
    }

    /**
     * 或且树, 如果 (a||b)&&(c||d<m))
     * 则symbol为1 leftTree为a||b rightTree为c||d<m
     */
    private static class OrAndTree {

        /**
         * 0->或 1->且
         */
        private Integer symbol;

        /**
         * 左子树
         */
        private OrAndTree leftTree;

        /**
         * 右子树
         */
        private OrAndTree rightTree;

        /**
         * 子节点才存在这个
         */
        private RuleTree ruleTree;

        public OrAndTree(Integer symbol) {
            this.symbol = symbol;
        }

        public OrAndTree(RuleTree ruleTree) {
            this.ruleTree = ruleTree;
        }

        /**
         * 计算结果
         *
         * @return
         */
        public Boolean makeResults(Map<String, Object> param, OrAndTree orAndTree) {
            Integer symbol = orAndTree.getSymbol();
            // 叶子结点
            if (symbol == null) {
                return orAndTree.getRuleTree().makeBooleanResult(param);
            }
            // 或
            if (symbol == 0) {
                Boolean left = makeResults(param, orAndTree.getLeftTree());
                Boolean right = makeResults(param, orAndTree.getRightTree());
                return left || right;
            }
            // 且
            if (symbol == 1) {
                Boolean left = makeResults(param, orAndTree.getLeftTree());
                Boolean right = makeResults(param, orAndTree.getRightTree());
                return left && right;
            }
            Asserts.throwException("符号类型不存在,:{}", symbol);
            return false;
        }

        public Integer getSymbol() {
            return symbol;
        }

        public OrAndTree getLeftTree() {
            return leftTree;
        }

        public void setLeftTree(OrAndTree leftTree) {
            this.leftTree = leftTree;
        }

        public OrAndTree getRightTree() {
            return rightTree;
        }

        public void setRightTree(OrAndTree rightTree) {
            this.rightTree = rightTree;
        }

        public RuleTree getRuleTree() {
            return ruleTree;
        }
    }


}
