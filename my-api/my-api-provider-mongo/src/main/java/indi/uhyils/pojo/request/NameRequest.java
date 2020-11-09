package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 名称请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月02日 16时35分
 */
public class NameRequest extends DefaultRequest {
    /**
     * 名称, 人名,地名,文件名
     */
    private String name;

    public static NameRequest build(String name) {
        NameRequest build = new NameRequest();
        build.name = name;
        return build;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
