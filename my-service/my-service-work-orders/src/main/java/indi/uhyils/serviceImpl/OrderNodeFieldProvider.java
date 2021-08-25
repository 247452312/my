package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderNodeFieldDao;
import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderNodeFieldProvider extends BaseDefaultProvider<OrderNodeFieldDO> implements indi.uhyils.protocol.rpc.provider.OrderNodeFieldProvider {

    @Resource
    private OrderNodeFieldDao dao;


    @Override
    public OrderNodeFieldDao getDao() {
        return dao;
    }

    public void setDao(OrderNodeFieldDao dao) {
        this.dao = dao;
    }
}
