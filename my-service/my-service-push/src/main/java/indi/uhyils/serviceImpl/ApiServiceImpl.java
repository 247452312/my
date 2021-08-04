package indi.uhyils.serviceImpl;

import indi.uhyils.dao.ApiDao;
import indi.uhyils.pojo.model.ApiEntity;
import indi.uhyils.pojo.request.GetByArgsAndGroupRequest;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.base.Page;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ApiService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@RpcService
public class ApiServiceImpl extends BaseDefaultServiceImpl<ApiEntity> implements ApiService {

    @Resource
    private ApiDao dao;

    @Override
    public ApiDao getDao() {
        return dao;
    }

    public void setDao(ApiDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<Page<ApiEntity>> getByArgsAndGroup(GetByArgsAndGroupRequest request) {
        List<Arg> args = request.getArgs();
        Boolean paging = request.getPaging();
        Arg e = new Arg();
        e.setName("api_group_id");
        e.setSymbol("=");
        e.setData(request.getGroupId());
        args.add(e);
        if (paging) {
            ArrayList<ApiEntity> byArgs = getDao().getByArgs(args, request.getPage(), request.getSize());
            int count = getDao().countByArgs(request.getArgs());
            Page<ApiEntity> build = Page.build(request, byArgs, count, (count / request.getSize()) + 1);
            return ServiceResult.buildSuccessResult("查询成功", build);
        } else {
            ArrayList<ApiEntity> byArgs = getDao().getByArgsNoPage(args);
            int count = getDao().countByArgs(request.getArgs());
            Page<ApiEntity> build = Page.build(request, byArgs, count, null);
            return ServiceResult.buildSuccessResult("查询成功", build);
        }
    }
}
