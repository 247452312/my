package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderApiDao;
import indi.uhyils.pojo.DO.OrderApiDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderApiProvider extends BaseDefaultProvider<OrderApiDO> implements indi.uhyils.protocol.rpc.provider.OrderApiProvider {

    @Resource
    private OrderApiDao dao;


    @Override
    public OrderApiDao getDao() {
        return dao;
    }

    public void setDao(OrderApiDao dao) {
        this.dao = dao;
    }
}
