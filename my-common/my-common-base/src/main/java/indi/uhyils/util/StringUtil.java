package indi.uhyils.util;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月31日 21时33分
 */
public final class StringUtil {

    /**
     * 匹配大写字符
     */
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 包含数量
     *
     * @param str
     * @param ch
     *
     * @return
     */
    public static int containsCount(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (ch == c) {
                count++;
            }
        }
        return count;
    }

    /**
     * 父类字符串包含子类字符串数量
     *
     * @param str
     * @param ch
     *
     * @return
     */
    public static int containsCount(String str, String ch) {
        int count = 0;
        char[] chars = ch.toCharArray();
        for (int i = 0; i < str.length() - ch.length(); i++) {
            boolean successMatch = true;
            for (int j = 0; j < chars.length; j++) {
                if (!Objects.equals(chars[j], str.charAt(i + j))) {
                    successMatch = false;
                    break;
                }
            }
            if (successMatch) {
                count++;
            }
        }
        return count;
    }

    /**
     * 转化方法名称到属性名称
     *
     * @param methodName
     *
     * @return
     */
    public static String transMethodNameToFieldName(String methodName) {
        if (methodName.startsWith("get")) {
            methodName = methodName.substring(3);
        } else if (methodName.startsWith("is")) {
            methodName = methodName.substring(2);
        }
        // 小写第一个字母
        return firstToLowerCase(methodName);
    }

    /**
     * 返回字符串是否为空
     *
     * @param cs
     *
     * @return
     */
    public static Boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * <p>
     * 首字母转换小写
     * </p>
     *
     * @param param 需要转换的字符串
     *
     * @return 转换好的字符串
     */
    public static String firstToLowerCase(String param) {
        if (isEmpty(param)) {
            return "";
        }
        return param.substring(0, 1).toLowerCase() + param.substring(1);
    }

    /**
     * 大写字母转下划线+小写字母
     *
     * @param fieldName
     *
     * @return
     */
    public static String toUnderline(String fieldName) {
        /** 驼峰转下划线,效率比上面高 */
        Matcher matcher = humpPattern.matcher(fieldName);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 是否不为空
     *
     * @param cs
     *
     * @return
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }
}
