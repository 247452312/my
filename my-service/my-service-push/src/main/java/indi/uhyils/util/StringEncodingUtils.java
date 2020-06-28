package indi.uhyils.util;

/**
 * 字符串编码
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月28日 08时26分
 */
public class StringEncodingUtils {

    /**
     * Unicode 标识
     */
    private static final String UNICODE_MARK = "\\u";

    public static String decodeUnicodeString(String str) {
        int index;
        String result = str;
        while ((index = result.indexOf(UNICODE_MARK)) != -1) {
            String substring = result.substring(index + 2, index + 6);
            result = result.replace(result.substring(index, index + 6), String.valueOf((char) Integer.parseInt(substring.substring(0, 4), 16)));
        }
        return result;
    }

    /**
     * 将字符串解码为 utf-8格式
     *
     * @param text
     * @return
     */
    public static String decodeUTF8tring(String text) {
        return text;
    }
}
