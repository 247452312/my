package indi.uhyils.service;

import indi.uhyils.pojo.model.DocumentEntity;
import indi.uhyils.pojo.request.GetDocumentChildensRequest;
import indi.uhyils.pojo.request.GetDocumentRootByIframeRequest;
import indi.uhyils.pojo.response.GetDocumentChildensResponse;
import indi.uhyils.pojo.response.GetDocumentRootByIframeResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

/**
 * 文档service
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月27日 01时06分
 */
public interface DocumentService extends DefaultEntityService<DocumentEntity> {


    /**
     * 获取一个文档的子节点
     *
     * @param request
     * @return
     */
    ServiceResult<GetDocumentChildensResponse> getDocumentChildens(GetDocumentChildensRequest request);


    /**
     * 根据适用场景获取一个文档的多个根节点
     *
     * @param request
     * @return
     */
    ServiceResult<GetDocumentRootByIframeResponse> getDocumentRootByIframe(GetDocumentRootByIframeRequest request);

}
