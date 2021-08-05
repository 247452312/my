package indi.uhyils.pojo.response;

import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月05日 09时17分
 */
public class GetLogTypeResponse implements Serializable {

    /**
     * 日志名称
     */
    private String name;

    /**
     * 日志类型编号
     */
    private Integer code;

    public static GetLogTypeResponse build(String name, Integer code) {
        GetLogTypeResponse build = new GetLogTypeResponse();
        build.name = name;
        build.code = code;
        return build;
    }

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
