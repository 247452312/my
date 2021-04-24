package indi.uhyils.exception;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月24日 11时25分
 * @Version 1.0
 */
public class ProtocolNotFoundException extends UserException {
    public ProtocolNotFoundException() {
        super("协议未找到");
    }
}
