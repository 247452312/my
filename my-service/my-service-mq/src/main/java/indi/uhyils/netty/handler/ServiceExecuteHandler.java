package indi.uhyils.netty.handler;

import indi.uhyils.netty.model.ProtocolParsingModel;
import indi.uhyils.service.MqService;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
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
        Class<? extends MqService> serviceClass = service.getClass();
        String methodName = ppm.getMethodName();
        Class[] paramsType = ppm.getParamsType();
        Method method = serviceClass.getMethod(methodName, paramsType);
        Object[] params = ppm.getParams();
        Object returnObj = method.invoke(service, params);
        // 将返回值解析为正规的返回类型
        Boolean responseStatus = ppm.getFunction().transformEntry(chx, returnObj);
        if (responseStatus && LogUtil.isDebugEnabled(this)) {
            LogUtil.debug(this, "消息接收 回应成功:" + ppm.getIp());
        }
    }
}
