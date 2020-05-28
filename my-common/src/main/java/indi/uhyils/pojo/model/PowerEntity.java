package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.DataEntity;

import java.util.Objects;

/**
 * 权限(零散)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时26分
 */
public class PowerEntity extends DataEntity {

    /**
     * 接口名称
     */
    private String interfaceName;
    /**
     * 方法名称
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerEntity that = (PowerEntity) o;
        return Objects.equals(interfaceName, that.interfaceName) &&
                Objects.equals(methodName, that.methodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interfaceName, methodName);
    }
}
