package indi.uhyils.pojo.response;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年11月26日 09时47分
 */
public class InvokeResponse implements Serializable {

    /**
     * 结果列
     */
    private JSONArray result;


    public JSONArray getResult() {
        return result;
    }

    public void setResult(JSONArray result) {
        this.result = result;
    }
}
