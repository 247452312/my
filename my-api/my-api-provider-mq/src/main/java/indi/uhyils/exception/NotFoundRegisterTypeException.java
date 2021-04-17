package indi.uhyils.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月16日 09时09分
 */
public class NotFoundRegisterTypeException extends UserException {

    public NotFoundRegisterTypeException() {
        super("没有发现或未支持指定注册者参数");
    }
}
