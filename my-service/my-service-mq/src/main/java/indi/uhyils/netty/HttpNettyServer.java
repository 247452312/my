package indi.uhyils.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月23日 10时45分
 */
public class HttpNettyServer {
    /**
     * 主线程,单线程
     */
    private EventLoopGroup bossGroup;
    /**
     * 工作线程,多线程
     */
    private EventLoopGroup workerGroup;

    public HttpNettyServer() {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.ERROR))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("test", new Test());
                            // 请求解码器
                            p.addLast("http-decoder", new HttpRequestDecoder());
                            // 将HTTP消息的多个部分合成一条完整的HTTP消息
                            p.addLast("http-aggregator", new HttpObjectAggregator(65535));
                            // 自定义处理handler
                            p.addLast("http-server", new HttpHandler());
                        }
                    });

            b.bind(8080).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        HttpNettyServer httpHandler = new HttpNettyServer();
//        System.in.read();
        Connection connection = DriverManager.getConnection("jdbc:mysql://8.131.77.8:3306/my_user", "root", "123456");
        int k = 1;
        Statement preparedStatement = connection.createStatement();
        for (int i = 0; i < 1000000; i++) {
            System.out.println(i);
            preparedStatement.execute("insert into test(id,value) values(" + i + "," + k + ")");
            if (k > 10) {
                k = 1;
            }
        }
        connection.commit();
    }

    private static class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
            System.out.println(msg);
            if (msg.method() == HttpMethod.GET) {
//                System.out.println(getGetParamsFromChannel(fullHttpRequest));
//                String data = "GET method over";
//                ByteBuf buf = copiedBuffer(data, CharsetUtil.UTF_8);
//                response = responseOK(HttpResponseStatus.OK, buf);

            } else if (msg.method() == HttpMethod.POST) {

                Map<String, Object> postParamsFromChannel = getPostParamsFromChannel(msg);
                System.out.println(postParamsFromChannel);
                String data = "POST method over";
            }
            // 发送响应
            ctx.writeAndFlush(1).addListener(ChannelFutureListener.CLOSE);
        }

        /*
         * 获取POST方式传递的参数
         */
        private Map<String, Object> getPostParamsFromChannel(FullHttpRequest fullHttpRequest) {

            Map<String, Object> params = new HashMap<String, Object>();

            if (fullHttpRequest.method() == HttpMethod.POST) {
                // 处理POST请求
                String strContentType = fullHttpRequest.headers().get("Content-Type").trim();
                if (strContentType.contains("x-www-form-urlencoded")) {
                    params = getFormParams(fullHttpRequest);
                } else if (strContentType.contains("application/json")) {
                    try {
                        params = getJSONParams(fullHttpRequest);
                    } catch (UnsupportedEncodingException e) {
                        return null;
                    }
                } else {
                    return null;
                }
                return params;
            } else {
                return null;
            }
        }

        /*
         * 解析from表单数据（Content-Type = x-www-form-urlencoded）
         */
        private Map<String, Object> getFormParams(FullHttpRequest fullHttpRequest) {
            Map<String, Object> params = new HashMap<String, Object>();

            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), fullHttpRequest);
            List<InterfaceHttpData> postData = decoder.getBodyHttpDatas();

            for (InterfaceHttpData data : postData) {
                if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                    MemoryAttribute attribute = (MemoryAttribute) data;
                    params.put(attribute.getName(), attribute.getValue());
                }
            }

            return params;
        }

        /*
         * 解析json数据（Content-Type = application/json）
         */
        private Map<String, Object> getJSONParams(FullHttpRequest fullHttpRequest) throws UnsupportedEncodingException {
            Map<String, Object> params = new HashMap<String, Object>();

            ByteBuf content = fullHttpRequest.content();
            byte[] reqContent = new byte[content.readableBytes()];
            content.readBytes(reqContent);
            String strContent = new String(reqContent, "UTF-8");

            JSONObject jsonParams = JSONObject.parseObject(strContent);
            for (Object key : jsonParams.keySet()) {
                params.put(key.toString(), jsonParams.get(key));
            }

            return params;
        }
    }

    private static class Test extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            byte[] dst = new byte[msg.readableBytes()];
            msg.getBytes(0, dst);

            String s = new String(dst, StandardCharsets.UTF_8);
            System.out.println(s);


        }

    }
}
