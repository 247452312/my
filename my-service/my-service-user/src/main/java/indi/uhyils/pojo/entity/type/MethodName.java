package indi.uhyils.pojo.entity.type;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月28日 13时23分
 */
public class MethodName implements BaseType {

    /**
     * 方法名称
     */
    private String methodName;

    public MethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }
}
