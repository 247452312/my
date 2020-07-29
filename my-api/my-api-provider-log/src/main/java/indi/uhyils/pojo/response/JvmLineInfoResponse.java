package indi.uhyils.pojo.response;

import java.io.Serializable;
import java.util.List;

/**
 * 首页 JVM 线信息
 * Echart的参数形式
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 11时17分
 */
public class JvmLineInfoResponse implements Serializable {

    /**
     * 名称
     */
    private String name;
    /**
     * 类型 'line'
     */
    private String type;

    /**
     * 数据
     */
    private List<Double> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public static JvmLineInfoResponse build(String name, List<Double> data) {
        JvmLineInfoResponse build = new JvmLineInfoResponse();
        build.setName(name);
        build.setType("line");
        build.setData(data);
        return build;

    }


}
