package indi.uhyils.mysql.util;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年05月16日 14时50分
 */
public final class StringUtil {

    /**
     * sql引用
     */
    private static final String PREFIX = "`";


    /**
     * 去除sql引用标识
     *
     * @param str
     *
     * @return
     */
    public static String cleanQuotation(String str) {
        if (indi.uhyils.util.StringUtil.isEmpty(str)) {
            return str;
        }
        if (str.startsWith(PREFIX)) {
            str = str.substring(1);
        }
        if (str.endsWith(PREFIX)) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 切割出字段的index
     *
     * @param fieldName
     *
     * @return
     */
    public static Integer subFieldIndex(String fieldName) {
        int left = fieldName.indexOf("(");
        int right = fieldName.indexOf(")");
        if (left == -1 || right == -1) {
            return null;
        }
        String index = fieldName.substring(left + 1, right);
        return Integer.parseInt(index);
    }
}
