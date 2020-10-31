package indi.uhyils.serviceImpl;

import indi.uhyils.dao.DocumentDao;
import indi.uhyils.pojo.model.DocumentEntity;
import indi.uhyils.pojo.request.GetDocumentChildensRequest;
import indi.uhyils.pojo.request.GetDocumentRootByIframeRequest;
import indi.uhyils.pojo.response.GetDocumentChildensResponse;
import indi.uhyils.pojo.response.GetDocumentRootByIframeResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.DocumentService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月27日 01时06分
 */
@Service(group = "${spring.profiles.active}")
public class DocumentServiceImpl extends BaseDefaultServiceImpl<DocumentEntity> implements DocumentService {

    @Autowired
    private DocumentDao dao;


    public DocumentDao getDao() {
        return dao;
    }

    public void setDao(DocumentDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<GetDocumentChildensResponse> getDocumentChildens(GetDocumentChildensRequest request) {
        String id = request.getId();
        List<DocumentEntity> list = dao.getChildensById(id);
        return ServiceResult.buildSuccessResult("查询子节点成功", GetDocumentChildensResponse.build(list), request);
    }

    @Override
    public ServiceResult<GetDocumentRootByIframeResponse> getDocumentRootByIframe(GetDocumentRootByIframeRequest request) {
        Integer iframe = request.getIframe();
        List<DocumentEntity> documentEntitys = dao.getDocumentRootByIframe(iframe);
        GetDocumentRootByIframeResponse get = GetDocumentRootByIframeResponse.build(documentEntitys);
        return ServiceResult.buildSuccessResult("查询根节点成功", get, request);
    }
}
