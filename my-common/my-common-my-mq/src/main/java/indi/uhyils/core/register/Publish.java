package indi.uhyils.core.register;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月16日 09时05分
 */
public class Publish extends AbstractRegister {
    public Publish(String ip, Integer port) {
        super(ip, port);
    }

    @Override
    public RegisterType getRegisterType() {
        return RegisterType.PUBLISH;
    }
}
