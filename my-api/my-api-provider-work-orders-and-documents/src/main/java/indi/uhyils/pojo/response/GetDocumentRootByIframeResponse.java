package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.DocumentEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 根据适用场景获取一个文档的根节点
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月28日 09时09分
 */
public class GetDocumentRootByIframeResponse implements Serializable {


    /**
     * 文档本体
     */
    private List<DocumentEntity> documentEntities;

    public static GetDocumentRootByIframeResponse build(List<DocumentEntity> documentEntitys) {
        GetDocumentRootByIframeResponse build = new GetDocumentRootByIframeResponse();
        build.setDocumentEntities(documentEntitys);
        return build;

    }

    public List<DocumentEntity> getDocumentEntities() {
        return documentEntities;
    }

    public void setDocumentEntities(List<DocumentEntity> documentEntities) {
        this.documentEntities = documentEntities;
    }
}
