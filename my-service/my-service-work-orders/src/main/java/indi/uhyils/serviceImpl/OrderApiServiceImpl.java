package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderApiDao;
import indi.uhyils.pojo.model.OrderApiEntity;
import indi.uhyils.service.OrderApiService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderApiServiceImpl extends BaseDefaultServiceImpl<OrderApiEntity> implements OrderApiService {

    @Autowired
    private OrderApiDao dao;


    public OrderApiDao getDao() {
        return dao;
    }

    public void setDao(OrderApiDao dao) {
        this.dao = dao;
    }
}
