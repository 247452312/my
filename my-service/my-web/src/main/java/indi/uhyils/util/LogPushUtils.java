package indi.uhyils.util;

import com.alibaba.fastjson.JSONObject;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.util.disruptor.JsonEvent;
import indi.uhyils.util.disruptor.JsonEventConsumer;
import indi.uhyils.util.disruptor.JsonEventFactory;
import indi.uhyils.util.disruptor.JsonEventProducerWithTranslator;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 日志推送请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月08日 14时36分
 */
public class LogPushUtils {


    private static JsonEventProducerWithTranslator producer;

    static {
        ThreadPoolExecutor jsonExecutor = new ThreadPoolExecutor(1, 3, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
        JsonEventFactory factory = new JsonEventFactory();
        int bufferSize = 1024 * 8;
        Disruptor<JsonEvent> disruptor = new Disruptor<JsonEvent>(factory, bufferSize, jsonExecutor);
        disruptor.handleEventsWith(SpringUtil.getBean(JsonEventConsumer.class));
        disruptor.start();
        RingBuffer<JsonEvent> ringBuffer = disruptor.getRingBuffer();
        producer = new JsonEventProducerWithTranslator(ringBuffer);
    }

    public static void pushLog(String exceptionDetail, String interfaceName, String methodName, Object params, LinkNode<String> link, HttpServletRequest request, String token) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("class", "indi.uhyils.pojo.model.LogEntity");
        // 说明有错误信息,类型是错误
        if (exceptionDetail != null && !"".equals(exceptionDetail)) {
            jsonObject.put("logType", LogTypeEnum.ERROR.getType());
            jsonObject.put("exceptionDetail", exceptionDetail);
        } else {
            jsonObject.put("logType", LogTypeEnum.NOMAL.getType());
        }

        jsonObject.put("interfaceName", interfaceName);
        jsonObject.put("methodName", methodName);
        jsonObject.put("params", JSONObject.toJSONString(params));
        jsonObject.put("time", System.currentTimeMillis());
        jsonObject.put("ip", getIPAddress(request));
        jsonObject.put("userId", token);
        jsonObject.put("link", parseString(link));

        String json = jsonObject.toJSONString();
        producer.onData(json, token);
    }

    private static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static String parseString(LinkNode<String> link) {
        StringBuilder sb = new StringBuilder();
        LinkNode<String> p = link;
        while (p != null) {
            sb.append(p.getData());
            sb.append("-->");
            p = p.getLinkNode();
        }
        return sb.toString();
    }
}
