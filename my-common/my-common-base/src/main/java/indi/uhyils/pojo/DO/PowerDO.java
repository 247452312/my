package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDoDO;
import java.util.Objects;

/**
 * 权限(零散)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时26分
 */
public class PowerDO extends BaseDoDO {

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;

    public static PowerDO build(PowerSimpleDO powerSimpleDO) {
        PowerDO powerEntity = new PowerDO();
        powerEntity.setInterfaceName(powerSimpleDO.getInterfaceName());
        powerEntity.setMethodName(powerSimpleDO.getMethodName());
        return powerEntity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return Boolean.TRUE;
        }
        if (o == null || getClass() != o.getClass()) {
            return Boolean.FALSE;
        }
        PowerDO that = (PowerDO) o;
        return Objects.equals(interfaceName, that.interfaceName) &&
               Objects.equals(methodName, that.methodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interfaceName, methodName);
    }
}
