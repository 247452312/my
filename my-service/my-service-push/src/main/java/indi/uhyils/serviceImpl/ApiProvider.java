package indi.uhyils.serviceImpl;

import indi.uhyils.dao.ApiDao;
import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.pojo.DTO.request.GetByArgsAndGroupRequest;
import indi.uhyils.pojo.cqe.Arg;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@RpcService
public class ApiProvider extends BaseDefaultProvider<ApiDO> implements indi.uhyils.protocol.rpc.provider.ApiProvider {

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
    public ServiceResult<Page<ApiDO>> getByArgsAndGroup(GetByArgsAndGroupRequest request) {
        List<Arg> args = request.getArgs();
        Boolean paging = request.getPaging();
        Arg e = new Arg();
        e.setName("api_group_id");
        e.setSymbol("=");
        e.setData(request.getGroupId());
        args.add(e);
        if (paging) {
            ArrayList<ApiDO> byArgs = getDao().getByArgs(args, request.getPage(), request.getSize());
            int count = getDao().countByArgs(request.getArgs());
            Page<ApiDO> build = Page.build(request, byArgs, count, (count / request.getSize()) + 1);
            return ServiceResult.buildSuccessResult("查询成功", build);
        } else {
            ArrayList<ApiDO> byArgs = getDao().getByArgsNoPage(args);
            int count = getDao().countByArgs(request.getArgs());
            Page<ApiDO> build = Page.build(request, byArgs, count, null);
            return ServiceResult.buildSuccessResult("查询成功", build);
        }
    }
}
