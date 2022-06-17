package indi.uhyils.core.register;

import indi.uhyils.enums.OutDealTypeEnum;
import indi.uhyils.enums.RegisterType;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月16日 09时05分
 */
public class Publish extends AbstractRegister {

    private Publish(String url, Integer port, OutDealTypeEnum outDealTypeEnum) {
        super(url, null, outDealTypeEnum);
    }

    private Publish(String channelId, OutDealTypeEnum outDealTypeEnum) {
        super(null, channelId, outDealTypeEnum);
    }

    public static Publish buildUrlRegister(String url, OutDealTypeEnum outDealTypeEnum) {
        return new Publish(url, null, outDealTypeEnum);
    }

    public static Publish buildChannelRegister(String channelId, OutDealTypeEnum outDealTypeEnum) {
        return new Publish(channelId, outDealTypeEnum);
    }

    @Override
    public RegisterType getRegisterType() {
        return RegisterType.PUBLISH;
    }
}
