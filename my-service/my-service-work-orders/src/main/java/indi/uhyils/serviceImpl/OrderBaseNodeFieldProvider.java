package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderBaseNodeFieldDao;
import indi.uhyils.pojo.DO.OrderBaseNodeFieldDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderBaseNodeFieldProvider extends BaseDefaultProvider<OrderBaseNodeFieldDO> implements indi.uhyils.protocol.rpc.provider.OrderBaseNodeFieldProvider {

    @Resource
    private OrderBaseNodeFieldDao dao;


    @Override
    public OrderBaseNodeFieldDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseNodeFieldDao dao) {
        this.dao = dao;
    }
}
