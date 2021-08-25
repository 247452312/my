package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderNodeFieldValueDao;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月15日 16时15分56秒
 */
@RpcService
public class OrderNodeFieldValueProvider extends BaseDefaultProvider<OrderNodeFieldValueDO> implements indi.uhyils.protocol.rpc.provider.OrderNodeFieldValueProvider {
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
