package indi.uhyils.core.register.logic;

import indi.uhyils.exception.ExpressionInvalidException;
import java.util.Arrays;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月18日 15时57分
 */
public class LogicEngineHelper {

    private static final Symbol[] VALUES = Symbol.values();

    static {
        //从大到小排列
        Arrays.sort(VALUES, (o1, o2) -> Integer.compare(o2.getSym().length(), o1.getSym().length()));
    }

    /**
     * 判断key是否符合表达式
     *
     * @param expression 表达式
     * @param key        替换字符串中keyName的值
     * @param keyName    值的名称
     *
     * @return
     *
     * @throws ExpressionInvalidException
     */
    public static Boolean check(String expression, Object key, String keyName) throws ExpressionInvalidException {
        if (!expression.contains(keyName)) {
            throw new ExpressionInvalidException(expression);
        }
        expression = expression.trim();
        int end;
        while ((end = expression.indexOf(")")) != -1) {
            int start = expression.lastIndexOf("(", end);
            String substring = expression.substring(start + 1, end);
            expression = expression.replaceAll(expression.substring(start, end + 1), check(substring, key, keyName).toString());
        }
        if (expression.contains("&&")) {
            //根据&&分开字符串
            String[] split = expression.split("&&");
            for (String s : split) {
                Boolean check = check(s, key, keyName);
                if (Boolean.FALSE.equals(check)) {
                    return Boolean.FALSE;
                }
            }
            return Boolean.TRUE;
        }
        if (expression.contains("||")) {
            //根据&&分开字符串
            String[] split = expression.split("\\|\\|");
            for (String s : split) {
                Boolean check = check(s, key, keyName);
                if (Boolean.TRUE.equals(check)) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }

        for (Symbol value : VALUES) {
            final String sym = value.getSym();
            if (expression.contains(sym)) {
                String[] split = expression.split(sym);
                if (split.length != 2) {
                    throw new ExpressionInvalidException(expression);
                }
                if (split[1].trim().equals(keyName)) {
                    //这时表达式为 1 < key
                    return value.trans().compare(key, split[0]);
                } else {
                    // 这时表达式为 key < 1
                    return value.compare(key, split[1]);
                }
            }
        }
        if ("true".equalsIgnoreCase(expression)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(expression)) {
            return Boolean.FALSE;
        }
        throw new ExpressionInvalidException(expression);
    }


    /**
     * 判断key是否符合表达式 默认在其中使用key作为关键字
     *
     * @param expression
     * @param key
     *
     * @return
     */
    public static boolean check(String expression, Object key) throws ExpressionInvalidException {
        return check(expression, key, "key");
    }
}
