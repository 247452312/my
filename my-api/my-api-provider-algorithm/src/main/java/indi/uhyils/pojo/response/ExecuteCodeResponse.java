package indi.uhyils.pojo.response;

import java.io.Serializable;


/**
 * 解析代码的返回值
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月10日 08时46分
 */
public class ExecuteCodeResponse implements Serializable {

    /**
     * json值
     */
    private String responseJson;

    public static ExecuteCodeResponse build(String responseJson) {
        ExecuteCodeResponse build = new ExecuteCodeResponse();
        build.responseJson = responseJson;
        return build;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }
}
