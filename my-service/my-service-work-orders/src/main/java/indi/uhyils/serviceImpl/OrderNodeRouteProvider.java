package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderNodeRouteDao;
import indi.uhyils.pojo.DO.OrderNodeRouteDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderNodeRouteProvider extends BaseDefaultProvider<OrderNodeRouteDO> implements indi.uhyils.protocol.rpc.OrderNodeRouteProvider {

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
