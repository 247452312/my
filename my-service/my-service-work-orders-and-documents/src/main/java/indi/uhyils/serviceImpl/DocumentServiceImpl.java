package indi.uhyils.serviceImpl;

import org.apache.dubbo.config.annotation.Service;
import indi.uhyils.annotation.NoToken;
import indi.uhyils.content.Content;
import indi.uhyils.dao.DocumentDao;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.*;
import indi.uhyils.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

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
}
