package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 获取方法是否允许通过的方法
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 10时44分
 */
public class GetMethodDisableRequest extends DefaultRequest {

    /**
     * 接口名称
     */
    private String className;
    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 方法的类型 1->读接口 2->写接口
     */
    private Integer methodType;


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

    public Integer getMethodType() {
        return methodType;
    }

    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }
}
