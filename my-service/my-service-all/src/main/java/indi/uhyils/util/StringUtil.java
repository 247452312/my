package indi.uhyils.util;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月31日 21时33分
 */
public final class StringUtil {

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

    public static boolean isNotBlack(CharSequence cs) {
        return !isBlack(cs);
    }

    public static boolean isBlack(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
