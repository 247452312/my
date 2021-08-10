package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月10日 09时07分
 */
public class ExecuteCodeRequest extends DefaultRequest {

    /**
     * 类体
     */
    private String classValue;

    public String getClassValue() {
        return classValue;
    }

    public void setClassValue(String classValue) {
        this.classValue = classValue;
    }
}
