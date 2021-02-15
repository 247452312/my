package indi.uhyils.mq.util;


import com.alibaba.fastjson.JSON;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.mq.pojo.mqinfo.InterfaceCallInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;

import java.nio.charset.StandardCharsets;

/**
 * 微服务
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月18日 12时02分
 */
public class ServiceUtil {

    /**
     * 获取微服务的interface调用日志
     *
     * @param interfaceName  调用接口名称
     * @param methodName     调用方法名称
     * @param success        是否成功
     * @param runTime        调用时间
     * @param requestLength  请求大小
     * @param responseLength 返回大小
     * @param jvmUniqueMark  JVM信息
     * @return
     */
    public static InterfaceCallInfo getInterfaceCallInfo(String interfaceName, String methodName, Boolean success, Long runTime, Integer requestLength, Integer responseLength, JvmUniqueMark jvmUniqueMark) {
        InterfaceCallInfo interfaceCallInfo = new InterfaceCallInfo();
        interfaceCallInfo.setInterfaceName(interfaceName);
        interfaceCallInfo.setMethodName(methodName);
        interfaceCallInfo.setSuccess(success);
        interfaceCallInfo.setRunTime(runTime);
        interfaceCallInfo.setJvmUniqueMark(jvmUniqueMark);
        interfaceCallInfo.setRequestLength(requestLength);
        interfaceCallInfo.setResponseLength(responseLength);
        interfaceCallInfo.setTime(System.currentTimeMillis());
        return interfaceCallInfo;
    }

    /**
     * 获取微服务的interface调用日志
     *
     * @param interfaceName 调用接口名称
     * @param methodName    调用方法名称
     * @param serviceCode   返回信息
     * @param runTime       调用时间
     * @param request       请求
     * @param response      返回
     * @param jvmUniqueMark JVM信息
     * @return
     */
    public static InterfaceCallInfo getInterfaceCallInfo(String interfaceName, String methodName, Integer serviceCode, Long runTime, Object request, Object response, JvmUniqueMark jvmUniqueMark) {
        int requestLength = JSON.toJSONString(request).getBytes(StandardCharsets.UTF_8).length;
        int responseLength = JSON.toJSONString(response).getBytes(StandardCharsets.UTF_8).length;
        boolean success = ServiceCode.SUCCESS.getText().equals(serviceCode) || ServiceCode.SUCCESS_REDIS.getText().equals(serviceCode);
        return getInterfaceCallInfo(interfaceName, methodName, success, runTime, requestLength, responseLength, jvmUniqueMark);
    }
}
