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
    private Provider(String url, Integer port, OutDealTypeEnum outDealTypeEnum) {
        super(url, null, outDealTypeEnum);
    }

    private Provider(String channelId, OutDealTypeEnum outDealTypeEnum) {
        super(null, channelId, outDealTypeEnum);
    }

    public static Provider buildUrlRegister(String url, OutDealTypeEnum outDealTypeEnum) {
        return new Provider(url, null, outDealTypeEnum);
    }

    public static Provider buildChannelRegister(String channelId, OutDealTypeEnum outDealTypeEnum) {
        return new Provider(channelId, outDealTypeEnum);
    }

    @Override
    public RegisterType getRegisterType() {
        return RegisterType.PROVIDER;
    }
}
