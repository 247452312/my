package indi.uhyils.mysql.pojo.DTO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    private List<Map<String, Object>> result;


    public static NodeInvokeResult build(List<FieldInfo> fieldInfos, List<Map<String, Object>> result) {
        NodeInvokeResult build = new NodeInvokeResult();
        build.setFieldInfos(fieldInfos);
        build.setResult(result);
        return build;

    }

    public List<FieldInfo> getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(List<FieldInfo> fieldInfos) {
        this.fieldInfos = fieldInfos;
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }
}
