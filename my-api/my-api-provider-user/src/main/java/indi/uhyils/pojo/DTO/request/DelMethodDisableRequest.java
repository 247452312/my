package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.DTO.request.base.DefaultRequest;

/**
 * 删除请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 21时29分
 */
public class DelMethodDisableRequest extends DefaultRequest {

    /**
     * 接口名称
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

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
}
