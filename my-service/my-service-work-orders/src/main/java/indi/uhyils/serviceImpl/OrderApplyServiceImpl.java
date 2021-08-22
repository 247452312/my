package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderApplyDao;
import indi.uhyils.pojo.model.OrderApplyDO;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.OrderApplyService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderApplyServiceImpl extends BaseDefaultServiceImpl<OrderApplyDO> implements OrderApplyService {

    @Resource
    private OrderApplyDao dao;


    @Override
    public OrderApplyDao getDao() {
        return dao;
    }

    public void setDao(OrderApplyDao dao) {
        this.dao = dao;
    }
}
