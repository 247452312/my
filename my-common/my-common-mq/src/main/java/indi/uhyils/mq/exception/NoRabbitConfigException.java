package indi.uhyils.mq.exception;

/**
 * rabbit config没有发现的错误
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月18日 09时21分
 */
public class NoRabbitConfigException extends Exception {

    public NoRabbitConfigException() {
        super("rabbit配置为空");
    }
}
