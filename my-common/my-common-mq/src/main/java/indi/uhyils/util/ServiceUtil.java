package indi.uhyils.util;

import indi.uhyils.pojo.mqinfo.InterfaceCallInfo;
import indi.uhyils.pojo.mqinfo.JvmUniqueMark;

/**
 * 微服务
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月18日 12时02分
 */
public class ServiceUtil {

    public static InterfaceCallInfo getInterfaceCallInfo(String interfaceName, String methodName, Boolean success, Long runTime,JvmUniqueMark jvmUniqueMark) {
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
