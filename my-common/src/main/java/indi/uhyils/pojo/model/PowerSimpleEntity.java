package indi.uhyils.pojo.model;

import java.io.Serializable;

/**
 * 简易版PowerEntity 用来项目启动时初始化权限
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 08时08分
 */
public class PowerSimpleEntity implements Serializable {

    private String interfaceName;

    private String methodName;


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

    public static PowerSimpleEntity build(String interfaceName, String methodName) {
        PowerSimpleEntity powerSimpleEntity = new PowerSimpleEntity();
        powerSimpleEntity.setInterfaceName(interfaceName);
        powerSimpleEntity.setMethodName(methodName);
        return powerSimpleEntity;
    }
}
