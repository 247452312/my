package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.DocumentEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 获取一个文档的子节点返回
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月28日 09时00分
 */
public class GetDocumentChildensResponse implements Serializable {

    /**
     * 子节点
     */
    List<DocumentEntity> documents;

    public static GetDocumentChildensResponse build(List<DocumentEntity> documents) {
        GetDocumentChildensResponse build = new GetDocumentChildensResponse();
        build.setDocuments(documents);
        return build;

    }

    public List<DocumentEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentEntity> documents) {
        this.documents = documents;
    }
}
