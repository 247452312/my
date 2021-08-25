package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderLogDao;
import indi.uhyils.pojo.DO.OrderLogDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderLogProvider extends BaseDefaultProvider<OrderLogDO> implements indi.uhyils.protocol.rpc.provider.OrderLogProvider {

    @Resource
    private OrderLogDao dao;


    @Override
    public OrderLogDao getDao() {
        return dao;
    }

    public void setDao(OrderLogDao dao) {
        this.dao = dao;
    }
}
