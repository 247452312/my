package indi.uhyils.netty.handler;

import indi.uhyils.netty.model.ProtocolParsingModel;
import indi.uhyils.netty.util.NettyChannelUtil;
import indi.uhyils.pojo.request.DefaultLinkRequest;
import indi.uhyils.service.MqService;
import indi.uhyils.util.SpringUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月24日 11时36分
 * @Version 1.0
 */
public class ServiceExecuteHandler extends SimpleChannelInboundHandler<ProtocolParsingModel> {

    private final MqService service;

    public ServiceExecuteHandler() {
        service = SpringUtil.getBean(MqService.class);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chx, ProtocolParsingModel ppm) throws Exception {
        if (ppm.isKeepAlive()) {
            // 保存channel
            NettyChannelUtil.addChannelIfNoContains(chx.channel());
        }
        ChannelFuture channelFuture;
        // 如果需要service
        if (ppm.getNeedService()) {
            Class<? extends MqService> serviceClass = service.getClass();
            String methodName = ppm.getMethodName();
            Class[] paramsType = ppm.getParamsType();
            Method method = serviceClass.getMethod(methodName, paramsType);
            Object[] params = ppm.getParams();
            if (params[0] instanceof DefaultLinkRequest && ppm.isKeepAlive()) {
                DefaultLinkRequest request = (DefaultLinkRequest) params[0];
                request.setChannelId(chx.channel().id().asLongText());
            }
            Object returnObj = method.invoke(service, params);
            // 将返回值解析为正规的返回类型
            channelFuture = ppm.getFunction().transformEntry(chx, returnObj);
        } else {
            // 如果不需要service,则执行回调函数
            byte[] bytes = ppm.getReturnByteFunction().get();
            channelFuture = chx.writeAndFlush(bytes);
        }
        if (ppm.isKeepAlive() && channelFuture != null) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        } else {
            chx.close();
        }
    }
}
