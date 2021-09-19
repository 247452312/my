package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.BaseDTO;

/**
 * 方法禁用信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 10时46分
 */
public class MethodDisableDTO implements BaseDTO {

    /**
     * 类名称
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 类型
     * 如果是接口: 0->无 1->读接口禁用 2->写接口禁用 3->全部禁用
     * 如果是方法 0->无 1->禁用
     */
    private Integer disableType;

    public static MethodDisableDTO build(String className, String methodName, Integer disableType) {
        MethodDisableDTO build = new MethodDisableDTO();
        build.className = className;
        build.methodName = methodName;
        build.disableType = disableType;

        return build;

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getDisableType() {
        return disableType;
    }

    public void setDisableType(Integer disableType) {
        this.disableType = disableType;
    }
}
