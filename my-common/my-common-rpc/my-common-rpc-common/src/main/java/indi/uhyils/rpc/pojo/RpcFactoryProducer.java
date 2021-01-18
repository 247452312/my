package indi.uhyils.rpc.pojo;

import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.pojo.request.RpcRequestFactory;
import indi.uhyils.rpc.pojo.response.RpcResponseFactory;

import static indi.uhyils.rpc.enums.RpcTypeEnum.*;

/**
 * Rpc工厂生成器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月19日 10时07分
 */
public class RpcFactoryProducer {

    public static RpcFactory build(RpcTypeEnum rpcTypeEnum) {
        switch (rpcTypeEnum) {
            case REQUEST:
                return RpcRequestFactory.getInstance();
            case RESPONSE:
                return RpcResponseFactory.getInstance();
            default:
                return null;
        }

    }
}
