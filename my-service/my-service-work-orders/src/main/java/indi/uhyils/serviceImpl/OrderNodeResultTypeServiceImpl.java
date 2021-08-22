package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderNodeResultTypeDao;
import indi.uhyils.pojo.model.OrderNodeResultTypeDO;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.OrderNodeResultTypeService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderNodeResultTypeServiceImpl extends BaseDefaultServiceImpl<OrderNodeResultTypeDO> implements OrderNodeResultTypeService {

    @Resource
    private OrderNodeResultTypeDao dao;


    @Override
    public OrderNodeResultTypeDao getDao() {
        return dao;
    }

    public void setDao(OrderNodeResultTypeDao dao) {
        this.dao = dao;
    }
}
