package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.ApiSubscribeDao;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.ApiSubscribeEntity;
import indi.uhyils.pojo.request.SubscribeRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
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

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> subscribe(SubscribeRequest request) {
        ApiSubscribeEntity entity = ApiSubscribeEntity.build(request);
        /*查重-> 同一个用户,同一个api就算做重复*/
        int repeat = dao.checkSubscribeRepeat(entity);
        if (repeat == 0) {
            entity.preInsert(request);
            int insert = dao.insert(entity);
            return ServiceResult.buildSuccessResult("订阅成功", insert == 1, request);
        } else {
            return ServiceResult.buildFailedResult("不允许重复订阅", false, request);
        }

    }
}
