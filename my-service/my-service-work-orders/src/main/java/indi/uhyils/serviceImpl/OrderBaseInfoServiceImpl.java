package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderBaseInfoDao;
import indi.uhyils.pojo.model.OrderBaseInfoEntity;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.OrderBaseInfoService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderBaseInfoServiceImpl extends BaseDefaultServiceImpl<OrderBaseInfoEntity> implements OrderBaseInfoService {

    @Resource
    private OrderBaseInfoDao dao;


    public OrderBaseInfoDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseInfoDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<ArrayList<OrderBaseInfoEntity>> getAllBaseOrderIdAndName(DefaultRequest request) {
        ArrayList<OrderBaseInfoEntity> result = dao.getAllBaseOrderIdAndName();
        return ServiceResult.buildSuccessResult(result, request);
    }
}
