package indi.uhyils.rpc.exchange.pojo.data.factory;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月15日 17时04分
 */
public class Tt implements Serializable {

    private String name;

    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
