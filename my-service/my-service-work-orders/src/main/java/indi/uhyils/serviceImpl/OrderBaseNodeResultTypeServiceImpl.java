package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderBaseNodeResultTypeDao;
import indi.uhyils.pojo.model.OrderBaseNodeResultTypeDO;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.OrderBaseNodeResultTypeService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderBaseNodeResultTypeServiceImpl extends BaseDefaultServiceImpl<OrderBaseNodeResultTypeDO> implements OrderBaseNodeResultTypeService {

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
