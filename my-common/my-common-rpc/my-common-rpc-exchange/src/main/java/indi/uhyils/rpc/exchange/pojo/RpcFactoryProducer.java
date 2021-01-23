package indi.uhyils.rpc.exchange.pojo;

import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exchange.pojo.request.RpcRequestFactory;
import indi.uhyils.rpc.exchange.pojo.response.RpcResponseFactory;

/**
 * Rpc工厂生成器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月19日 10时07分
 */
public class RpcFactoryProducer {

    public static RpcFactory build(RpcTypeEnum rpcTypeEnum) {
        switch (rpcTypeEnum) {
            default:
            case REQUEST:
                return RpcRequestFactory.getInstance();
            case RESPONSE:
                return RpcResponseFactory.getInstance();
        }

    }
}
