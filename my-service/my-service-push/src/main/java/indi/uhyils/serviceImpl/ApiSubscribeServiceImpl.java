package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.ApiSubscribeDao;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.ApiSubscribeDO;
import indi.uhyils.pojo.request.SubscribeRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ApiSubscribeService;
import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@RpcService
public class ApiSubscribeServiceImpl extends BaseDefaultServiceImpl<ApiSubscribeDO> implements ApiSubscribeService {

    @Resource
    private ApiSubscribeDao dao;

    @Override
    public ApiSubscribeDao getDao() {
        return dao;
    }

    public void setDao(ApiSubscribeDao dao) {
        this.dao = dao;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> subscribe(SubscribeRequest request) {
        ApiSubscribeDO entity = ApiSubscribeDO.build(request);
        /*查重-> 同一个用户,同一个api就算做重复*/
        int repeat = dao.checkSubscribeRepeat(entity);
        if (repeat == 0) {
            entity.preInsert(request);
            int insert = dao.insert(entity);
            return ServiceResult.buildSuccessResult("订阅成功", insert == 1);
        } else {
            return ServiceResult.buildFailedResult("不允许重复订阅", false);
        }

    }
}
