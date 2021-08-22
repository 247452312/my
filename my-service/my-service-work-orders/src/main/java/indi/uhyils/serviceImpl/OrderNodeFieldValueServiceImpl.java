package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderNodeFieldValueDao;
import indi.uhyils.pojo.model.OrderNodeFieldValueDO;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.OrderNodeFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月15日 16时15分56秒
 */
@RpcService
public class OrderNodeFieldValueServiceImpl extends BaseDefaultServiceImpl<OrderNodeFieldValueDO> implements OrderNodeFieldValueService {
    @Autowired
    private OrderNodeFieldValueDao dao;

    @Override
    public OrderNodeFieldValueDao getDao() {
        return this.dao;
    }

    public void setDao(OrderNodeFieldValueDao dao) {
        this.dao = dao;
    }


}
