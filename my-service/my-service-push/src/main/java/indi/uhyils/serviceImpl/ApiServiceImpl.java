package indi.uhyils.serviceImpl;

import indi.uhyils.dao.ApiDao;
import indi.uhyils.pojo.model.ApiEntity;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.ApiService;
import indi.uhyils.util.ApiUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public ServiceResult<String> test(IdRequest request) {
        ApiEntity apiEntity = dao.getById(request.getId());
        Integer apiOrder = apiEntity.getApiOrder();
        if (apiOrder != 1) {
            return ServiceResult.buildFailedResult("错误,此api顺位不为1,不能执行", null, request);
        }
        String apiGroup = apiEntity.getApiGroup();
        List<ApiEntity> list = dao.getGroupByGroupId(apiGroup);
        String s = ApiUtils.callApi(list, request.getUser(), new HashMap<>());
        return ServiceResult.buildSuccessResult("执行成功", s, request);
    }
}
