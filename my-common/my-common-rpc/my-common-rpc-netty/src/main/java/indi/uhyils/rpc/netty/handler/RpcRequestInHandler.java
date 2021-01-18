package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.RpcExtensionLoader;
import indi.uhyils.rpc.filter.base.RpcFilter;
import indi.uhyils.rpc.filter.template.ProviderRequestByteFilter;
import indi.uhyils.rpc.filter.template.ProviderRequestDataFilter;
import indi.uhyils.rpc.filter.template.ProviderResponseByteFilter;
import indi.uhyils.rpc.filter.template.ProviderResponseDataFilter;
import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.pojo.RpcData;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 10时58分
 */
public class RpcRequestInHandler extends SimpleChannelInboundHandler<ByteBuf> {


    /**
     * 回调
     */
    private final RpcCallBack callback;
    /**
     * 生产者接收请求byte拦截器
     */
    private LinkedList<ProviderRequestByteFilter> providerRequestByteFilters = new LinkedList<>();
    /**
     * 生产者接收请求data拦截器
     */
    private LinkedList<ProviderRequestDataFilter> providerRequestDataFilters = new LinkedList<>();
    /**
     * 生产者接收请求处理完成后的data拦截器
     */
    private LinkedList<ProviderResponseDataFilter> providerResponseDataFilters = new LinkedList<>();
    /**
     * 生产者接收请求处理完成后byte拦截器
     */
    private LinkedList<ProviderResponseByteFilter> providerResponseByteFilters = new LinkedList<>();


    public RpcRequestInHandler(RpcCallBack callback) {
        this.callback = callback;
        List extensionByClass = RpcExtensionLoader.getExtensionByClass(RpcFilter.class);
        for (Object byClass : extensionByClass) {
            if (byClass instanceof ProviderRequestByteFilter) {
                providerRequestByteFilters.add((ProviderRequestByteFilter) byClass);
            }
            if (byClass instanceof ProviderRequestDataFilter) {
                providerRequestDataFilters.add((ProviderRequestDataFilter) byClass);
            }
            if (byClass instanceof ProviderResponseDataFilter) {
                providerResponseDataFilters.add((ProviderResponseDataFilter) byClass);
            }
            if (byClass instanceof ProviderResponseByteFilter) {
                providerResponseByteFilters.add((ProviderResponseByteFilter) byClass);
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        /*接收并释放byteBuf*/
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        // ProviderRequestByteFilter
        for (ProviderRequestByteFilter filter : providerRequestByteFilters) {
            bytes = filter.doFilter(bytes);
        }
        RpcData rpcData = callback.getRpcData(bytes);
        // ProviderRequestDataFilter
        for (ProviderRequestDataFilter filter : providerRequestDataFilters) {
            rpcData = filter.doFilter(rpcData);
        }
        String resultJson = callback.invoke(rpcData.content());
        LogUtil.info("--------------------------------------------------" + resultJson);
        RpcData assembly = callback.assembly(rpcData.unique(), resultJson);
        // ProviderResponseDataFilter
        for (ProviderResponseDataFilter filter : providerResponseDataFilters) {
            assembly = filter.doFilter(assembly);
        }
        byte[] responseBytes = assembly.toBytes();
        // ProviderResponseByteFilter
        for (ProviderResponseByteFilter filter : providerResponseByteFilters) {
            responseBytes = filter.doFilter(responseBytes);
        }
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(responseBytes);
        ctx.channel().writeAndFlush(buf);
    }
}
