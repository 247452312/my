package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 根据适用场景获取一个文档的根节点
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月28日 09时08分
 */
public class GetDocumentRootByIframeRequest extends DefaultRequest {

    /**
     * 使用场景代号
     */
    private Integer iframe;

    public Integer getIframe() {
        return iframe;
    }

    public void setIframe(Integer iframe) {
        this.iframe = iframe;
    }
}
