package indi.uhyils.core.register;

import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.RegisterType;

/**
 * 消息提供者
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月16日 09时02分
 */
public class Provider extends AbstractRegister {
    public Provider(String ip, Integer port, OutDealTypeEnum outDealTypeEnum) {
        super(ip, port, outDealTypeEnum);
    }

    @Override
    public RegisterType getRegisterType() {
        return RegisterType.PROVIDER;
    }
}
