package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.ApiDao;
import indi.uhyils.dao.ApiGroupDao;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.ApiDO;
import indi.uhyils.pojo.model.ApiGroupDO;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ApiGroupService;
import indi.uhyils.util.ApiUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@RpcService
public class ApiGroupServiceImpl extends BaseDefaultServiceImpl<ApiGroupDO> implements ApiGroupService {

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
        ApiGroupDO apiGroupEntity = dao.getById(request.getId());
        List<ApiDO> groupByGroupId = apiDao.getGroupByGroupId(apiGroupEntity.getId());
        HashMap<String, String> parameter = new HashMap<>(16);
        ApiUtils.callApi(groupByGroupId, request.getUser(), parameter);
        String resultFormat = apiGroupEntity.getResultFormat();
        resultFormat = ApiUtils.replaceString(parameter, resultFormat);
        return ServiceResult.buildSuccessResult("执行成功", resultFormat);
    }

    @Override
    public ServiceResult<ArrayList<ApiGroupDO>> getCanBeSubscribed(DefaultRequest request) {
        ArrayList<ApiGroupDO> result = dao.getCanBeSubscribed();
        return ServiceResult.buildSuccessResult("查询成功", result);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        ApiGroupDO byId = getDao().getById(idRequest.getId());
        if (byId == null) {
            return ServiceResult.buildFailedResult("查无此服务", null);
        }
        byId.setDeleteFlag(Boolean.TRUE);
        byId.preUpdate(idRequest);
        getDao().update(byId);
        int apiDelete = apiDao.deleteAllByGroup(byId);
        return ServiceResult.buildSuccessResult("删除成功", apiDelete);
    }
}
