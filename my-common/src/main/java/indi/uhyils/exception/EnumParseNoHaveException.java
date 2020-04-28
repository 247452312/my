package indi.uhyils.exception;

/**
 * enum找不到parse对象错误
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月27日 17时04分
 */
public class EnumParseNoHaveException extends Exception {

    public EnumParseNoHaveException(String msg) {
        super(msg);
    }

    public EnumParseNoHaveException() {
    }
}
