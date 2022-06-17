package indi.uhyils.core.register;

import indi.uhyils.enums.OutDealTypeEnum;
import indi.uhyils.enums.RegisterType;

/**
 * 消息提供者
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月16日 09时02分
 */
public class Subscriber extends AbstractRegister {

    private Subscriber(String url, Integer port, OutDealTypeEnum outDealTypeEnum) {
        super(url, null, outDealTypeEnum);
    }

    private Subscriber(String channelId, OutDealTypeEnum outDealTypeEnum) {
        super(null, channelId, outDealTypeEnum);
    }

    public static Subscriber buildUrlRegister(String url, OutDealTypeEnum outDealTypeEnum) {
        return new Subscriber(url, null, outDealTypeEnum);
    }

    public static Subscriber buildChannelRegister(String channelId, OutDealTypeEnum outDealTypeEnum) {
        return new Subscriber(channelId, outDealTypeEnum);
    }

    @Override
    public RegisterType getRegisterType() {
        return RegisterType.SUBSCRIBER;
    }
}
