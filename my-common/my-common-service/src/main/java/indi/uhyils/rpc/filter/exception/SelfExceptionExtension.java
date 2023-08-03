package indi.uhyils.rpc.filter.exception;

import indi.uhyils.exception.AssertException;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.content.RpcHeaderContext;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcBusinessException;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.exchange.pojo.data.factory.ResponseRpcFactory;
import indi.uhyils.rpc.exchange.pojo.data.factory.RpcFactoryProducer;
import indi.uhyils.rpc.exchange.pojo.head.RpcHeader;
import indi.uhyils.rpc.netty.spi.step.template.ProviderResponseExceptionExtension;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月05日 17时48分
 */
@RpcSpi(name = "selfExceptionExtension")
public class SelfExceptionExtension implements ProviderResponseExceptionExtension {

    @Override
    public RpcData onBusinessException(RpcData rpcData, RpcBusinessException e) throws InterruptedException {
        Throwable cause = e.getCause();
        if (cause instanceof AssertException) {
            return ((ResponseRpcFactory) RpcFactoryProducer.build(RpcTypeEnum.RESPONSE)).createAssertExceptionResponse(rpcData, (AssertException) cause);
        }
        return onThrowable(rpcData, cause);
    }

    @Override
    public RpcData onRpcException(RpcData rpcData, RpcException e) throws InterruptedException {
        return ((ResponseRpcFactory) RpcFactoryProducer.build(RpcTypeEnum.RESPONSE)).createErrorResponse(rpcData.unique(), e.getCause(), RpcHeaderContext.get().entrySet().stream().map(t -> new RpcHeader(t.getKey(), t.getValue())).toArray(RpcHeader[]::new));

    }

    @Override
    public RpcData onThrowable(RpcData rpcData, Throwable th) throws InterruptedException {
        ResponseRpcFactory build = (ResponseRpcFactory) RpcFactoryProducer.build(RpcTypeEnum.RESPONSE);
        Map<String, String> headerMap = RpcHeaderContext.get();
        RpcHeader[] rpcHeaders = new RpcHeader[0];
        if (headerMap != null) {
            rpcHeaders = headerMap.entrySet().stream().map(t -> new RpcHeader(t.getKey(), t.getValue())).toArray(RpcHeader[]::new);
        }
        return build.createErrorResponse(rpcData.unique(), th, rpcHeaders);
    }
}
