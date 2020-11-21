package indi.uhyils.mq.util;


import indi.uhyils.mq.pojo.mqinfo.InterfaceCallInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;

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
     * @param interfaceName 调用接口名称
     * @param methodName    调用方法名称
     * @param success       是否成功
     * @param runTime       调用时间
     * @param jvmUniqueMark JVM信息
     * @return
     */
    public static InterfaceCallInfo getInterfaceCallInfo(String interfaceName, String methodName, Boolean success, Long runTime, JvmUniqueMark jvmUniqueMark) {
        InterfaceCallInfo interfaceCallInfo = new InterfaceCallInfo();
        interfaceCallInfo.setInterfaceName(interfaceName);
        interfaceCallInfo.setMethodName(methodName);
        interfaceCallInfo.setSuccess(success);
        interfaceCallInfo.setRunTime(runTime);
        interfaceCallInfo.setJvmUniqueMark(jvmUniqueMark);
        interfaceCallInfo.setTime(System.currentTimeMillis());
        return interfaceCallInfo;
    }
}
