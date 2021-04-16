package indi.uhyils.core.register;

/**
 * 消息提供者
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月16日 09时02分
 */
public class Subscriber extends AbstractRegister {
    public Subscriber(String ip, Integer port) {
        super(ip, port);
    }

    @Override
    public RegisterType getRegisterType() {
        return RegisterType.SUBSCRIBER;
    }
}
