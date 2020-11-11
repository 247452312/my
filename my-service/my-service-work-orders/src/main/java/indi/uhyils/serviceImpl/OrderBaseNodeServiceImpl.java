package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderBaseNodeDao;
import indi.uhyils.pojo.model.OrderBaseNodeEntity;
import indi.uhyils.service.OrderBaseNodeService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderBaseNodeServiceImpl extends BaseDefaultServiceImpl<OrderBaseNodeEntity> implements OrderBaseNodeService {

    @Resource
    private OrderBaseNodeDao dao;


    public OrderBaseNodeDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseNodeDao dao) {
        this.dao = dao;
    }
}
