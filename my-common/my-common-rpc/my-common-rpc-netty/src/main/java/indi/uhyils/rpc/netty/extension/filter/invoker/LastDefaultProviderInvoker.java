package indi.uhyils.rpc.netty.extension.filter.invoker;

import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.extension.RpcExtensionLoader;
import indi.uhyils.rpc.netty.extension.RpcExtensionLoaderTypeEnum;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.step.template.ProviderRequestByteExtension;
import indi.uhyils.rpc.netty.extension.step.template.ProviderRequestDataExtension;
import indi.uhyils.rpc.netty.extension.step.template.ProviderResponseByteExtension;
import indi.uhyils.rpc.netty.extension.step.template.ProviderResponseDataExtension;
import indi.uhyils.rpc.pojo.RpcData;
import indi.uhyils.rpc.pojo.response.RpcResponseFactory;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 07时27分
 */
public class LastDefaultProviderInvoker implements RpcInvoker {

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

    private ChannelHandlerContext ctx;

    private ByteBuf msg;


    public LastDefaultProviderInvoker(RpcCallBack callback, ChannelHandlerContext ctx, ByteBuf msg) {
        this.callback = callback;
        this.ctx = ctx;
        this.msg = msg;
        providerRequestByteFilters = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_STEP, ProviderRequestByteExtension.class);
        providerRequestDataFilters = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_STEP, ProviderRequestDataExtension.class);
        providerResponseDataFilters = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_STEP, ProviderResponseDataExtension.class);
        providerResponseByteFilters = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_STEP, ProviderResponseByteExtension.class);
    }

    @Override
    public RpcResult invoke(FilterContext context) throws RpcException, ClassNotFoundException {
        RpcResult rpcResult = context.getRpcResult();
        if (rpcResult == null) {
            context.setRpcResult(new RpcResultImpl());
        }
        byte[] bytes = receiveByte(msg);
        // ProviderRequestByteFilter
        for (ProviderRequestByteExtension filter : providerRequestByteFilters) {
            bytes = filter.doFilter(bytes);
        }

        RpcData rpcData = callback.getRpcData(bytes);
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
            byte[] responseBytes = assembly.toBytes();
            // ProviderResponseByteFilter
            for (ProviderResponseByteExtension filter : providerResponseByteFilters) {
                responseBytes = filter.doFilter(responseBytes);
            }
            context.getRpcResult().set(responseBytes);
        } catch (ClassNotFoundException e) {
            LogUtil.error(this, e);
            RpcData assembly = RpcResponseFactory.getInstance().createErrorResponse(rpcData.unique(), e, null);
            context.getRpcResult().set(assembly.toBytes());
        }
        return context.getRpcResult();
    }

    private byte[] receiveByte(ByteBuf msg) {
        /*接收并释放byteBuf*/
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        return bytes;
    }

}
