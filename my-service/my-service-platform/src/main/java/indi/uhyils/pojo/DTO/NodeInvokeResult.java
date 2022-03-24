package indi.uhyils.pojo.DTO;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月24日 19时03分
 */
public class NodeInvokeResult implements Serializable {

    /**
     * 字段
     */
    private List<FieldInfo> fieldInfos;

    /**
     * 结果
     */
    private JSONArray jsonArray;

    public List<FieldInfo> getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(List<FieldInfo> fieldInfos) {
        this.fieldInfos = fieldInfos;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }
}
