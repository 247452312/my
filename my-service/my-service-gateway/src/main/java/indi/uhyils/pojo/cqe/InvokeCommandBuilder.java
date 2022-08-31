package indi.uhyils.pojo.cqe;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enums.InvokeTypeEnum;
import indi.uhyils.util.Asserts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月15日 09时19分
 */
public class InvokeCommandBuilder {

    /**
     * 参数
     */
    private List<Map<String, Object>> params = new ArrayList<>();

    /**
     * header
     */
    private Map<String, String> header = new HashMap<>();

    /**
     * 路径
     */
    private String path;

    /**
     * 调用类型
     */
    private InvokeTypeEnum invokeTypeEnum;

    public InvokeCommandBuilder() {
    }

    /**
     * 添加post参数
     *
     * @param postMap
     *
     * @return
     */
    public InvokeCommandBuilder addPostMap(Map<String, Object> postMap) {
        if (postMap != null) {
            putToFirst(postMap);
        }
        return this;
    }

    /**
     * 添加到第一个map
     *
     * @param result
     */
    private void putToFirst(Map<String, Object> result) {
        if (params.isEmpty()) {
            params.add(new HashMap<>());
        }
        final Map<String, Object> stringObjectMap = params.get(0);
        stringObjectMap.putAll(result);
    }

    /**
     * 添加get参数
     *
     * @param getMap
     *
     * @return
     */
    public InvokeCommandBuilder addGetMap(Map<String, String[]> getMap) {
        Map<String, Object> result = new HashMap<>();
        for (Entry<String, String[]> stringEntry : getMap.entrySet()) {
            final String key = stringEntry.getKey();
            final String[] value = stringEntry.getValue();
            if (value.length == 1) {
                result.put(key, value[0]);
            } else {
                result.put(key, value);
            }
        }
        if (result.size() != 0) {
            putToFirst(result);
        }
        return this;
    }

    /**
     * 添加path参数
     *
     * @param path
     *
     * @return
     */
    public InvokeCommandBuilder addPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * 添加自定义参数
     *
     * @param path rpc调用过来的参数形式
     *
     * @return
     */
    public InvokeCommandBuilder addArgs(Object[] path) {
        final List<JSONObject> collect = Arrays.stream(path).map(t -> JSON.parseObject(JSON.toJSONString(t))).collect(Collectors.toList());
        this.params.addAll(collect);
        return this;
    }

    /**
     * 添加自定义参数
     *
     * @param path mysql调用过来的形式
     *
     * @return
     */
    public InvokeCommandBuilder addArgs(Map<String, Object> path) {
        final JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(path));
        this.params.add(jsonObject);
        return this;
    }

    /**
     * 添加header参数
     *
     * @param header
     *
     * @return
     */
    public InvokeCommandBuilder addHeader(Map<String, String> header) {
        this.header.putAll(header);
        return this;
    }

    /**
     * 设置请求类型
     *
     * @param type 1->http 2->mysql 3->rpc 具体枚举可能变化,见{@link InvokeTypeEnum}
     *
     * @return
     */
    public InvokeCommandBuilder setType(Integer type) {
        this.invokeTypeEnum = InvokeTypeEnum.parse(type);
        return this;
    }

    /**
     * 构建command
     *
     * @return
     */
    public InvokeCommand build() {
        final InvokeCommand invokeCommand = new InvokeCommand();
        invokeCommand.setHeader(this.header);
        invokeCommand.setParams(this.params);
        invokeCommand.setPath(this.path);
        Asserts.assertTrue(this.invokeTypeEnum != null, "调用类型没有设置!");
        invokeCommand.setInvokeType(this.invokeTypeEnum.getCode());
        return invokeCommand;
    }

}
