package indi.uhyils.util;

import indi.uhyils.annotation.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
     * 返回字符串是否为空
     *
     * @param cs
     *
     * @return
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 大写字母转下划线+小写字母
     *
     * @param fieldName
     *
     * @return
     */
    @NotNull
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

    /**
     * 根据indexList将 srcStr切分成多块,其中切分字符为""
     *
     * @param srcStr
     * @param indexList
     *
     * @return
     */
    public static List<String> splitByIndexList(String srcStr, List<Integer> indexList) {
        return splitByIndexList(srcStr, "", indexList);
    }

    /**
     * 根据indexList将 srcStr切分成多块,其中切分字符为replaceStr
     *
     * @param srcStr
     * @param replaceStr
     * @param indexList
     *
     * @return
     */
    public static List<String> splitByIndexList(String srcStr, String replaceStr, List<Integer> indexList) {
        int lastIndex = srcStr.length() - 1;
        List<String> result = new ArrayList<>(indexList.size() + 1);
        for (int i = indexList.size() - 1; i >= 0; i--) {
            String substring = srcStr.substring(indexList.get(i), lastIndex);
            result.add(substring);
        }
        result.add(srcStr.substring(0, indexList.get(0)));

        Collections.reverse(result);
        return result.stream().filter(t -> t.startsWith(replaceStr)).map(t -> t.replaceFirst(replaceStr, "")).collect(Collectors.toList());
    }

    /**
     * 匹配并替换第一个
     *
     * @param str
     * @param replaceStr
     * @param targetStr
     *
     * @return
     */
    public static String replaceFirst(String str, String replaceStr, String targetStr) {
        int i = str.indexOf(replaceStr);
        if (i == -1) {
            return str;
        }
        int last = i + replaceStr.length();
        return str.substring(0, i) + targetStr + str.substring(last);
    }

    /**
     * 根据下标和长度替换字符串
     *
     * @param original   起始字符串
     * @param startIndex 起始下标
     * @param length     长度
     * @param targetStr  目标字符串
     *
     * @return
     */
    public static String replaceByIndexAndLength(String original, Integer startIndex, Integer length, String targetStr) {
        String firstStr = original.substring(0, startIndex);
        String secondStr = original.substring(startIndex + length);
        return String.format("%s%s%s", firstStr, targetStr, secondStr);
    }

    /**
     * 全包含
     *
     * @param targetStr
     * @param chars
     *
     * @return
     */
    public static boolean allContains(String targetStr, char... chars) {
        for (char aChar : chars) {
            if (!targetStr.contains(aChar + "")) {
                return false;
            }
        }
        return true;
    }


    /**
     * 全不包含
     *
     * @param targetStr 目标字符串
     * @param chars     目标字符组
     *
     * @return
     */
    public static boolean allNotContains(String targetStr, char... chars) {
        for (char aChar : chars) {
            if (targetStr.contains(aChar + "")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查找指定项
     *
     * @param str     原始字符串
     * @param findStr 待查找字符串
     *
     * @return
     */
    public static Map<Integer, String> find(String str, String findStr) {
        String[] split = findStr.split("\\*");
        List<List<Integer>> splitIndexList = new ArrayList<>();
        for (String item : split) {
            // 获取所有str中first的位置
            List<Integer> result = new ArrayList<>();
            int lastIndex = -1;
            while (true) {
                lastIndex = str.indexOf(item, lastIndex + 1);
                if (lastIndex == -1) {
                    break;
                }
                result.add(lastIndex);
            }
            splitIndexList.add(result);
        }
        if (split.length == 1) {
            List<Integer> integers = splitIndexList.get(0);
            Map<Integer, String> result = new HashMap<>();
            integers.stream().forEach(t -> result.put(t, findStr));
            return result;
        }
        // 以第一个分割块的结果为准
        List<Integer> integers = splitIndexList.get(0);
        Map<Integer, String> result = new HashMap<>();

        // 切分段,也是结束点
        Integer cutSegment = 0;
        // 遍历第一个切分块
        for (int index = 0; index != integers.size(); index++) {
            // 起始点
            Integer first = integers.get(index);
            if (first < cutSegment) {
                continue;
            }
            cutSegment = first + split[0].length();
            int splitIndex = 1;
            // 一直遍历到最后一个split,寻找符合条件的块
            while (splitIndex != split.length) {
                List<Integer> splitIndexs = splitIndexList.get(splitIndex);
                int splitForThisIndex = cutSegment + split[splitIndex].length();
                for (Integer integer : splitIndexs) {
                    if (integer <= cutSegment) {
                        continue;
                    }
                    cutSegment = integer;
                    break;
                }
                cutSegment = cutSegment + split[splitIndex].length();
                // 相等, 说明cutSegment没有变, 已经到了结束的地方,并且没有找到最后一个块
                if (splitForThisIndex == cutSegment) {
                    return result;
                }
                splitIndex++;
            }
            String substring = str.substring(first, cutSegment);
            result.put(first, substring);
        }
        return result;
    }

    /**
     * 判断首字母是否大写
     *
     * @param scopeName
     *
     * @return
     */
    public static boolean isFirstUpperCase(String scopeName) {
        if (isEmpty(scopeName)) {
            return false;
        }
        return Character.isUpperCase(scopeName.charAt(0));
    }

    /**
     * 将类全名转换成简单名称
     *
     * @param className
     *
     * @return
     */
    public static String transClassNameToSimpleName(String className) {
        int lastIndex = className.lastIndexOf(".");
        if (lastIndex == -1) {
            return className;
        }
        return className.substring(lastIndex + 1);
    }

    /**
     * 删除类全名中的泛型
     *
     * @param className
     *
     * @return
     */
    public static String removeGenericsFromClassNames(String className) {
        int start = className.indexOf("<");
        if (start == -1) {
            return className;
        }
        int end = start;
        int preCount = 1;
        for (int i = start + 1; i < className.length(); i++) {
            char c = className.charAt(i);
            if (c == '>') {
                preCount--;
                if (preCount == 0) {
                    end = i;
                    break;
                }
            } else if (c == '<') {
                preCount++;
            }
        }
        if (end != start) {
            String removeFirstName = className.substring(0, start) + className.substring(end + 1);
            return removeGenericsFromClassNames(removeFirstName);
        }
        return className;
    }

    /**
     * 忽略大小比较字符串
     *
     * @param s1
     * @param s2
     *
     * @return
     */
    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (s1 == null) {
            return false;
        }
        return s1.equalsIgnoreCase(s2);
    }
}
