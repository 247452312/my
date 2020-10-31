package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 获取一个文档的子节点
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月28日 08时58分
 */
public class GetDocumentChildensRequest extends DefaultRequest {

    /**
     * 指定文档的id
     */
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
