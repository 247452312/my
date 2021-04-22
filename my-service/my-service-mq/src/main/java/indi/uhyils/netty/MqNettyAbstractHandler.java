package indi.uhyils.netty;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.service.MqService;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Method;


/**
 * Mq的Netty会去调用netty原本的东西
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月22日 09时01分
 */
public abstract class MqNettyAbstractHandler extends SimpleChannelInboundHandler<ByteBuf> implements MqMessageReceivingNettyInterface {
    /**
     * 信息
     */
    private MqService mqService;

    /**
     * 子类通过实现这个方法解析协议,如果发现不是本协议,则返回null
     *
     * @param ctx
     * @param msg
     * @return
     */
    protected abstract NettyParsingPackage parsingNetty(ChannelHandlerContext ctx, ByteBuf msg);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        NettyParsingPackage nettyParsingPackage = parsingNetty(ctx, msg);
        // 如果没有解析协议, 说明此条信息不是本方法的协议跳过
        if (nettyParsingPackage == null) {
            return;
        }
        LogUtil.info(this, "接收到信息:" + JSONObject.toJSONString(nettyParsingPackage));
        Method method = MqService.class.getMethod(nettyParsingPackage.methodName, nettyParsingPackage.methodTypes);
        Object invoke = method.invoke(mqService, nettyParsingPackage.params);
        if (invoke != null) {
            byte[] bytes = JSONObject.toJSONBytes(invoke);
            ByteBuf buf = Unpooled.buffer();
            buf.writeBytes(bytes);
            ctx.channel().writeAndFlush(buf);
        }
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        this.mqService = applicationContext.getBean("MqServiceImpl", MqService.class);
    }

    /**
     * netty解析包
     */
    protected static class NettyParsingPackage {
        /**
         * ip
         */
        private String ip;
        /**
         * 端口
         */
        private Integer port;

        /**
         * 要调用的方法名
         */
        private String methodName;

        /**
         * 方法的入参类型
         */
        private Class[] methodTypes;

        /**
         * 方法的入参
         */
        private Object[] params;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public Class[] getMethodTypes() {
            return methodTypes;
        }

        public void setMethodTypes(Class[] methodTypes) {
            this.methodTypes = methodTypes;
        }

        public Object[] getParams() {
            return params;
        }

        public void setParams(Object[] params) {
            this.params = params;
        }
    }

}
