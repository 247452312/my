package indi.uhyils.serviceImpl;

import indi.uhyils.dao.ApiDao;
import indi.uhyils.pojo.model.ApiEntity;
import indi.uhyils.service.ApiService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@Service(group = "${spring.profiles.active}")
public class ApiServiceImpl extends BaseDefaultServiceImpl<ApiEntity> implements ApiService {

    @Autowired
    private ApiDao dao;

    public ApiDao getDao() {
        return dao;
    }

    public void setDao(ApiDao dao) {
        this.dao = dao;
    }
}
