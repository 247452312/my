package indi.uhyils.pojo.cqe;

import indi.uhyils.enums.InvokeTypeEnum;
import indi.uhyils.util.Asserts;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月15日 09时19分
 */
public class InvokeCommandBuilder {

    /**
     * 参数
     */
    private Map<String, Object> params = new HashMap<>();

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
        params.putAll(postMap);
        return this;
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
        params.putAll(result);
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
     * @param path mysql调用过来的形式
     *
     * @return
     */
    public InvokeCommandBuilder addArgs(Map<String, Object> path) {
        this.params.putAll(path);
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
