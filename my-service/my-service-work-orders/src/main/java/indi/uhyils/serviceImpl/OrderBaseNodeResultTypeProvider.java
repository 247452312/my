package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderBaseNodeResultTypeDao;
import indi.uhyils.pojo.DO.OrderBaseNodeResultTypeDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderBaseNodeResultTypeProvider extends BaseDefaultProvider<OrderBaseNodeResultTypeDO> implements indi.uhyils.protocol.rpc.provider.OrderBaseNodeResultTypeProvider {

    @Resource
    private OrderBaseNodeResultTypeDao dao;


    @Override
    public OrderBaseNodeResultTypeDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseNodeResultTypeDao dao) {
        this.dao = dao;
    }
}
