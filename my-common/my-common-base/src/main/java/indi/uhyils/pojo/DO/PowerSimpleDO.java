package indi.uhyils.pojo.DO;

import java.io.Serializable;

/**
 * 简易版PowerEntity 用来项目启动时初始化权限
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 08时08分
 */
public class PowerSimpleDO implements Serializable {

    private String interfaceName;

    private String methodName;

    public static PowerSimpleDO build(String interfaceName, String methodName) {
        PowerSimpleDO powerSimpleDO = new PowerSimpleDO();
        powerSimpleDO.setInterfaceName(interfaceName);
        powerSimpleDO.setMethodName(methodName);
        return powerSimpleDO;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
