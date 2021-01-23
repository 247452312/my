package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderApiDao;
import indi.uhyils.pojo.model.OrderApiEntity;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.OrderApiService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderApiServiceImpl extends BaseDefaultServiceImpl<OrderApiEntity> implements OrderApiService {

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
