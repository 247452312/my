package indi.uhyils.rpc.netty.spi.step.template.impl;

import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcBusinessException;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.exchange.pojo.data.factory.NormalRpcResponseFactory;
import indi.uhyils.rpc.exchange.pojo.data.factory.RpcFactoryProducer;
import indi.uhyils.rpc.netty.spi.step.template.ProviderResponseExceptionExtension;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月05日 17时18分
 */
public class DefaultProviderResponseExceptionExtensionImpl implements ProviderResponseExceptionExtension {

    private final NormalRpcResponseFactory rpcResponseFactory;

    public DefaultProviderResponseExceptionExtensionImpl() {
        rpcResponseFactory = (NormalRpcResponseFactory) RpcFactoryProducer.build(RpcTypeEnum.RESPONSE);
    }


    @Override
    public RpcData onBusinessException(RpcData rpcData, RpcBusinessException e) throws InterruptedException {
        // 业务异常
        return rpcResponseFactory.createErrorResponseByBusinessExceptionException(rpcData.unique(), e, null);
    }

    @Override
    public RpcData onRpcException(RpcData rpcData, RpcException e) throws InterruptedException {
        // rpc本身异常
        Throwable cause = e.getCause();
        return rpcResponseFactory.createErrorResponse(rpcData.unique(), cause, null);
    }

    @Override
    public RpcData onThrowable(RpcData rpcData, Throwable th) throws InterruptedException {
        // 其他异常
        return rpcResponseFactory.createErrorResponse(rpcData.unique(), th, null);
    }
}
