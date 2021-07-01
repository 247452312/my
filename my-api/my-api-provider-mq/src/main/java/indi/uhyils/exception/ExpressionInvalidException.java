package indi.uhyils.exception;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月18日 17时08分
 */
public class ExpressionInvalidException extends UserException {

    public ExpressionInvalidException(String expression) {
        super("您的表达式不符合规范:" + expression);
    }

    public ExpressionInvalidException(Object key, String symbol, String target) {
        super(String.format("您的表达式不符合规范,key: %s, symbol: %s, target: %s", key, symbol, target));
    }
}
