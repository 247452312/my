package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderNodeRouteDao;
import indi.uhyils.pojo.model.OrderNodeRouteEntity;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.OrderNodeRouteService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderNodeRouteServiceImpl extends BaseDefaultServiceImpl<OrderNodeRouteEntity> implements OrderNodeRouteService {

    @Resource
    private OrderNodeRouteDao dao;


    @Override
    public OrderNodeRouteDao getDao() {
        return dao;
    }

    public void setDao(OrderNodeRouteDao dao) {
        this.dao = dao;
    }
}
