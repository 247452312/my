package indi.uhyils.netty.handler;

import java.lang.reflect.Method;

import indi.uhyils.netty.model.ProtocolParsingModel;
import indi.uhyils.service.MqService;
import indi.uhyils.util.SpringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

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
        Class<? extends MqService> serviceClass = service.getClass();
        String methodName = ppm.getMethodName();
        Class[] paramsType = ppm.getParamsType();
        Method method = serviceClass.getMethod(methodName, paramsType);
        Object[] params = ppm.getParams();
        Object returnObj = method.invoke(service, params);
        // 将返回值解析为正规的返回类型
        Object obj = ppm.getFunction().apply(returnObj);
        send(chx, obj);
    }

    private void send(ChannelHandlerContext ctx, Object responseBytes) {
        ctx.channel().writeAndFlush(responseBytes);
    }
}
