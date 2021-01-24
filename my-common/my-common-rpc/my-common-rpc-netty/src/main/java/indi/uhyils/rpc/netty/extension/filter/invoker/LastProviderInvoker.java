package indi.uhyils.rpc.netty.extension.filter.invoker;

import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.exchange.pojo.RpcFactoryProducer;
import indi.uhyils.rpc.exchange.pojo.response.RpcResponseFactory;
import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.extension.RpcExtensionLoader;
import indi.uhyils.rpc.netty.extension.RpcExtensionLoaderTypeEnum;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.step.template.ProviderRequestByteExtension;
import indi.uhyils.rpc.netty.extension.step.template.ProviderRequestDataExtension;
import indi.uhyils.rpc.netty.extension.step.template.ProviderResponseByteExtension;
import indi.uhyils.rpc.netty.extension.step.template.ProviderResponseDataExtension;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 07时27分
 */
public class LastProviderInvoker implements RpcInvoker {

    /**
     * 回调
     */
    private final RpcCallBack callback;

    /**
     * 生产者接收请求byte拦截器
     */
    private List<ProviderRequestByteExtension> providerRequestByteFilters;
    /**
     * 生产者接收请求data拦截器
     */
    private List<ProviderRequestDataExtension> providerRequestDataFilters;
    /**
     * 生产者接收请求处理完成后的data拦截器
     */
    private List<ProviderResponseDataExtension> providerResponseDataFilters;
    /**
     * 生产者接收请求处理完成后byte拦截器
     */
    private List<ProviderResponseByteExtension> providerResponseByteFilters;

    private ByteBuf msg;


    public LastProviderInvoker(RpcCallBack callback, ByteBuf msg) {
        this.callback = callback;
        this.msg = msg;
        providerRequestByteFilters = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_STEP, ProviderRequestByteExtension.class);
        providerRequestDataFilters = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_STEP, ProviderRequestDataExtension.class);
        providerResponseDataFilters = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_STEP, ProviderResponseDataExtension.class);
        providerResponseByteFilters = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_STEP, ProviderResponseByteExtension.class);
    }

    @Override
    public RpcResult invoke(FilterContext context) throws RpcException, ClassNotFoundException {
        RpcResult rpcResult = context.getRpcResult();

        byte[] bytes = receiveByte(msg);
        // ProviderRequestByteFilter
        for (ProviderRequestByteExtension filter : providerRequestByteFilters) {
            bytes = filter.doFilter(bytes);
        }

        RpcData rpcData = callback.getRpcData(bytes);
        LogUtil.warn("生产者收到消息: " + rpcData.unique());
        try {
            // ProviderRequestDataFilter
            for (ProviderRequestDataExtension filter : providerRequestDataFilters) {
                rpcData = filter.doFilter(rpcData);
            }
            String resultJson = callback.invoke(rpcData.content());
            RpcData assembly = callback.assembly(rpcData.unique(), resultJson);
            // ProviderResponseDataFilter
            for (ProviderResponseDataExtension filter : providerResponseDataFilters) {
                assembly = filter.doFilter(assembly);
            }
            byte[] result = assembly.toBytes();
            for (ProviderResponseByteExtension providerResponseByteFilter : providerResponseByteFilters) {
                result = providerResponseByteFilter.doFilter(result);
            }
            context.put("result", result);
            RpcData byBytes = RpcFactoryProducer.build(RpcTypeEnum.RESPONSE).createByBytes(result);
            rpcResult.set(byBytes);
        } catch (ClassNotFoundException e) {
            LogUtil.error(this, e);
            RpcData assembly = RpcResponseFactory.getInstance().createErrorResponse(rpcData.unique(), e, null);
            context.put("result", assembly.toBytes());
            rpcResult.set(assembly);
        }
        return rpcResult;
    }

    private byte[] receiveByte(ByteBuf msg) {
        /*接收并释放byteBuf*/
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        return bytes;
    }

}
