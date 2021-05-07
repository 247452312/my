package indi.uhyils.core.register;

import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.RegisterType;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月16日 09时05分
 */
public class Consumer extends AbstractRegister {
    public Consumer(String ip, Integer port, OutDealTypeEnum outDealTypeEnum) {
        super(ip, port, null, outDealTypeEnum);
    }

    public Consumer(String channelId, OutDealTypeEnum outDealTypeEnum) {
        super(null, null, channelId, outDealTypeEnum);
    }

    @Override
    public RegisterType getRegisterType() {
        return RegisterType.COMSUMER;
    }
}
