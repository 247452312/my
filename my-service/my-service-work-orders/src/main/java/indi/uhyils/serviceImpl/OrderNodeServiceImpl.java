package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderNodeDao;
import indi.uhyils.pojo.model.OrderNodeEntity;
import indi.uhyils.service.OrderNodeService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderNodeServiceImpl extends BaseDefaultServiceImpl<OrderNodeEntity> implements OrderNodeService {

    @Resource
    private OrderNodeDao dao;


    public OrderNodeDao getDao() {
        return dao;
    }

    public void setDao(OrderNodeDao dao) {
        this.dao = dao;
    }
}
