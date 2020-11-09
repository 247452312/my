package indi.uhyils.serviceImpl;

import indi.uhyils.dao.OrderNodeResultTypeDao;
import indi.uhyils.pojo.model.OrderNodeResultTypeEntity;
import indi.uhyils.service.OrderNodeResultTypeService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderNodeResultTypeServiceImpl extends BaseDefaultServiceImpl<OrderNodeResultTypeEntity> implements OrderNodeResultTypeService {

    @Autowired
    private OrderNodeResultTypeDao dao;


    public OrderNodeResultTypeDao getDao() {
        return dao;
    }

    public void setDao(OrderNodeResultTypeDao dao) {
        this.dao = dao;
    }
}
