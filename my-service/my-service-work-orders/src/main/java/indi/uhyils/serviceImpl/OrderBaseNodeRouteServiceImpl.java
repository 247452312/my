package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderBaseNodeRouteDao;
import indi.uhyils.pojo.model.OrderBaseNodeRouteDO;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.OrderBaseNodeRouteService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderBaseNodeRouteServiceImpl extends BaseDefaultServiceImpl<OrderBaseNodeRouteDO> implements OrderBaseNodeRouteService {

    @Resource
    private OrderBaseNodeRouteDao dao;


    @Override
    public OrderBaseNodeRouteDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseNodeRouteDao dao) {
        this.dao = dao;
    }
}
