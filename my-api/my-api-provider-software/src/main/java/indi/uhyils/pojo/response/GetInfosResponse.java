package indi.uhyils.pojo.response;

import java.io.Serializable;

/**
 * redisInfo获取
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月14日 16时33分
 */
public class GetInfosResponse implements Serializable {
    /**
     * info的名称
     */
    private String name;

    /**
     * info的内容
     */
    private String value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static GetInfosResponse build(String name, String value) {
        GetInfosResponse getInfosResponse = new GetInfosResponse();
        getInfosResponse.setName(name);
        getInfosResponse.setValue(value);
        return getInfosResponse;
    }
}
