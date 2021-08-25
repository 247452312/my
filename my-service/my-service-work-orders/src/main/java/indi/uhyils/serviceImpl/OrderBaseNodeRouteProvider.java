package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderBaseNodeRouteDao;
import indi.uhyils.pojo.DO.OrderBaseNodeRouteDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderBaseNodeRouteProvider extends BaseDefaultProvider<OrderBaseNodeRouteDO> implements indi.uhyils.protocol.rpc.provider.OrderBaseNodeRouteProvider {

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
