package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 获取服务器名称
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 21时29分
 */
public class GetNameByIdRequest extends DefaultRequest {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
