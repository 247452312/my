package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderBaseNodeFieldDao;
import indi.uhyils.pojo.model.OrderBaseNodeFieldEntity;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.OrderBaseNodeFieldService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderBaseNodeFieldServiceImpl extends BaseDefaultServiceImpl<OrderBaseNodeFieldEntity> implements OrderBaseNodeFieldService {

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
