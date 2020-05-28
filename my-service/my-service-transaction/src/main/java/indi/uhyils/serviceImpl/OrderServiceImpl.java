package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.OrderDao;
import indi.uhyils.pojo.model.OrderEntity;
import indi.uhyils.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 13时14分
 */
@Service(group = "${spring.profiles.active}")
public class OrderServiceImpl extends DefaultServiceImpl<OrderEntity> implements OrderService {
    @Autowired
    private OrderDao dao;

    public OrderDao getDao() {
        return dao;
    }

    public void setDao(OrderDao dao) {
        this.dao = dao;
    }
}
