package indi.uhyils.serviceImpl;

import indi.uhyils.dao.ApiSubscribeDao;
import indi.uhyils.pojo.model.ApiSubscribeEntity;
import indi.uhyils.service.ApiSubscribeService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@Service(group = "${spring.profiles.active}")
public class ApiSubscribeServiceImpl extends BaseDefaultServiceImpl<ApiSubscribeEntity> implements ApiSubscribeService {

    @Autowired
    private ApiSubscribeDao dao;

    public ApiSubscribeDao getDao() {
        return dao;
    }

    public void setDao(ApiSubscribeDao dao) {
        this.dao = dao;
    }
}
