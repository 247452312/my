package indi.uhyils.pojo.response;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月21日 09时21分
 */
public class InvokeResponse implements Serializable {

    private JSONArray result;

    public static InvokeResponse build(JSONArray result) {
        InvokeResponse build = new InvokeResponse();
        build.setResult(result);
        return build;

    }

    public JSONArray getResult() {
        return result;
    }

    public void setResult(JSONArray result) {
        this.result = result;
    }
}
