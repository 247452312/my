package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.ApiDao;
import indi.uhyils.dao.ApiGroupDao;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.ApiEntity;
import indi.uhyils.pojo.model.ApiGroupEntity;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.ApiGroupService;
import indi.uhyils.util.ApiUtils;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@Service(group = "${spring.profiles.active}")
public class ApiGroupServiceImpl extends BaseDefaultServiceImpl<ApiGroupEntity> implements ApiGroupService {

    @Resource
    private ApiGroupDao dao;

    @Resource
    private ApiDao apiDao;

    @Override
    public ApiGroupDao getDao() {
        return dao;
    }

    public void setDao(ApiGroupDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<String> test(IdRequest request) {
        ApiGroupEntity apiGroupEntity = dao.getById(request.getId());
        List<ApiEntity> groupByGroupId = apiDao.getGroupByGroupId(apiGroupEntity.getId());
        HashMap<String, String> parameter = new HashMap<>(16);
        ApiUtils.callApi(groupByGroupId, request.getUser(), parameter);
        String resultFormat = apiGroupEntity.getResultFormat();
        resultFormat = ApiUtils.replaceString(parameter, resultFormat);
        return ServiceResult.buildSuccessResult("执行成功", resultFormat, request);
    }

    @Override
    public ServiceResult<ArrayList<ApiGroupEntity>> getCanBeSubscribed(DefaultRequest request) {
        ArrayList<ApiGroupEntity> result = dao.getCanBeSubscribed();
        return ServiceResult.buildSuccessResult("查询成功", result, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        ApiGroupEntity byId = getDao().getById(idRequest.getId());
        if (byId == null) {
            return ServiceResult.buildFailedResult("查无此服务", null, idRequest);
        }
        byId.setDeleteFlag(true);
        byId.preUpdate(idRequest);
        getDao().update(byId);
        int apiDelete = apiDao.deleteAllByGroup(byId);
        return ServiceResult.buildSuccessResult("删除成功", apiDelete, idRequest);
    }
}
